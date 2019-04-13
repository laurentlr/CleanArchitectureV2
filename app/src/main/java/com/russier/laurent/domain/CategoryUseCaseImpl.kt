package com.russier.laurent.domain

import io.reactivex.Single
import javax.inject.Inject

class CategoryUseCaseImpl @Inject constructor(
    private val repository: CategoryRepository
) : CategoryUseCase {

    override fun fetchCategories(): Single<List<Category>> {
        return repository.fetchCategories()
    }

    override fun fetchSubCategories(categoryId: String): Single<List<Category>> {
        return repository.fetchSubCategories(categoryId)
    }
}