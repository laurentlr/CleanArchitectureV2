package com.russier.laurent.data

import com.russier.laurent.data.database.CategoryDao
import com.russier.laurent.domain.Category
import com.russier.laurent.domain.CategoryRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryService: CategoryService,
    private val categoryMapper: CategoryMapper,
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override fun fetchCategories(): Single<List<Category>> {
        return getLocalOtherwiseApiCategories()
            .filter { it.parentId.isNullOrBlank() }
            .toList()
    }

    override fun fetchSubCategories(categoryId: String): Single<List<Category>> {
        return getLocalOtherwiseApiCategories()
            .filter { categoryId == it.parentId }
            .toList()
    }

    //local data and api data should have some real logic here
    private fun getLocalOtherwiseApiCategories(): Observable<Category> {
        return categoryDao
            //we should a a select with Id for suc categories
            .selectAll()
            .subscribeOn(Schedulers.io())
            .map { categoryMapper.transformFromDb(it) }
            .onErrorReturn { emptyList() }
            .flatMap { categories ->
                if (categories.isEmpty()) {
                    categoryService
                        .fetchCategories()
                        .map { categoryMapper.transform(it.resources) }
                        .doAfterSuccess { categoryDao.insert(categoryMapper.transformIntoDb(it)) }
                } else {
                    Single.just(categories)
                }
            }
            .flattenAsObservable { it }
    }
}