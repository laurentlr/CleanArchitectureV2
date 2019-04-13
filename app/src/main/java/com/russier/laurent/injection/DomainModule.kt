package com.russier.laurent.injection

import com.russier.laurent.domain.CategoryUseCase
import com.russier.laurent.domain.CategoryUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {

    @DomainScope
    @Binds
    abstract fun bindCategoryUseCase(categoryUseCase: CategoryUseCaseImpl): CategoryUseCase
}