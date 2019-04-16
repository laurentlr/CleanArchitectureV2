package com.russier.laurent.data

import com.russier.laurent.data.database.DbCategory
import com.russier.laurent.domain.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() {

    fun transform(jsonCategories: List<JsonCategory>): List<Category> {
        return jsonCategories.map { Category(it.id.toString(), it.name, it.parent?.id?.toString()) }
    }

    fun transformIntoDb(categories: List<Category>): List<DbCategory> {
        return categories.map { DbCategory(it.id, it.name, it.parentId) }
    }

    fun transformFromDb(dbCategories: List<DbCategory>): List<Category> {
        return dbCategories.map { Category(it.id, it.name, it.parentId) }
    }

}
