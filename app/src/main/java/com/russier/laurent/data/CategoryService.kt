package com.russier.laurent.data

import io.reactivex.Single
import retrofit2.http.GET

interface CategoryService {

    @GET("categories.json")
    fun fetchCategories(): Single<JsonCategories>
}
