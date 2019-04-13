package com.russier.laurent.domain

import io.reactivex.Single

class CategoryUseCaseImpl(
    private val repository: CategoryRepository
) : CategoryUseCase {

    override fun fetchCategories(): Single<List<Category>> {
        return repository.fetchCategories()
    }

    override fun fetchSubCategories(categoryId: String): Single<List<Category>> {
        return repository.fetchSubCategories(categoryId)
    }
}