package com.russier.laurent.injection

import com.russier.laurent.domain.CategoryUseCase
import dagger.Component

@DomainScope
@Component(dependencies = [DataComponent::class], modules = [DomainModule::class])
interface DomainComponent {

    fun categoryUseCase(): CategoryUseCase
}