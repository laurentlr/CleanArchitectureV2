package com.russier.laurent.domain

import io.reactivex.Single

interface CategoryUseCase {

    fun fetchCategories(): Single<List<Category>>

    fun fetchSubCategories(categoryId: String): Single<List<Category>>
}