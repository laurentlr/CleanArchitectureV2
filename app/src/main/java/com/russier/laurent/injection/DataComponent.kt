package com.russier.laurent.injection

import com.russier.laurent.domain.CategoryRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface DataComponent {

    fun categoryRepository(): CategoryRepository
}