package com.russier.laurent.injection

import com.russier.laurent.ui.category.CategoryActivity
import com.russier.laurent.ui.subcategory.SubCategoriesActivity
import dagger.Component

@PresenterScope
@Component(dependencies = [DomainComponent::class], modules = [PresenterModule::class])
interface PresenterComponent {

    fun inject(categoryActivity: CategoryActivity)

    fun inject(categoryActivity: SubCategoriesActivity)
}