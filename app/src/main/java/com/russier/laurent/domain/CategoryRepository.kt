package com.russier.laurent.domain

import io.reactivex.Single

interface CategoryRepository {

    fun fetchCategories(): Single<List<Category>>
}