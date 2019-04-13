package com.russier.laurent.data

import com.russier.laurent.domain.Category

class CategoryMapper {

    fun transform(jsonCategories: List<JsonCategory>): List<Category> {
        return jsonCategories.map { Category(it.id.toString(), it.name) }
    }

}
