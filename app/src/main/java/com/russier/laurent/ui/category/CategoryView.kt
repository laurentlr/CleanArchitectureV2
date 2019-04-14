package com.russier.laurent.ui.category

import com.russier.laurent.domain.Category

interface CategoryView {

    fun displayCategories(categories: List<Category>)

    fun displayError()
}