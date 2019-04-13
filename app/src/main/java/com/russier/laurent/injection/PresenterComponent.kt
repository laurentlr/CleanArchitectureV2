package com.russier.laurent.injection

import com.russier.laurent.ui.CategoryActivity
import dagger.Component

@PresenterScope
@Component(dependencies = [DomainComponent::class], modules = [PresenterModule::class])
interface PresenterComponent {

    fun inject(categoryActivity: CategoryActivity)
}