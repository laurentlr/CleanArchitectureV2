package com.russier.laurent.injection

import com.russier.laurent.ui.category.CategoryPresenter
import com.russier.laurent.ui.category.CategoryPresenterImpl
import com.russier.laurent.ui.category.CategoryView
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [PresenterModule.Declaration::class])
class PresenterModule constructor(private val view: CategoryView) {

    @PresenterScope
    @Provides
    internal fun provideView(): CategoryView = view

    @Module
    interface Declaration {

        @PresenterScope
        @Binds
        fun bindCategoriesPresenter(categoriesPresenterImpl: CategoryPresenterImpl): CategoryPresenter
    }
}