package com.russier.laurent.ui

import com.russier.laurent.domain.Category

interface CategoriesView {

    fun displayCategories(categories: List<Category>)

    fun displayError()
}