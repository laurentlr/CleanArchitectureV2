package com.russier.laurent.ui.subcategory

import com.russier.laurent.domain.Category

interface SubCategoryView {

    fun displaySubCategories(subCategories: List<Category>)

    fun displayError()

}
