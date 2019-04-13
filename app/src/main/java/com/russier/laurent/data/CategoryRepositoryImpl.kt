package com.russier.laurent.data

import com.russier.laurent.domain.Category
import com.russier.laurent.domain.CategoryRepository
import io.reactivex.Single

class CategoryRepositoryImpl(
    private val categoryService: CategoryService,
    private val categoryMapper: CategoryMapper
) : CategoryRepository {

    override fun fetchCategories(): Single<List<Category>> {
        return categoryService
            .fetchCategories()
            .map { categoryMapper.transform(it.resources) }
            .flattenAsObservable { it }
            .filter { it.parentId.isNullOrBlank() }
            .toList()
            .doAfterSuccess { /* TODO save*/ }

    }

    override fun fetchSubCategories(categoryId: String): Single<List<Category>> {
        return categoryService
            .fetchCategories()
            .map { categoryMapper.transform(it.resources) }
            .flattenAsObservable { it }
            .filter { categoryId == it.parentId }
            .toList()
    }
}