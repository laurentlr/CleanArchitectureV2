package com.russier.laurent.injection

import com.russier.laurent.ui.CategoriesPresenter
import com.russier.laurent.ui.CategoriesPresenterImpl
import com.russier.laurent.ui.CategoriesView
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [PresenterModule.Declaration::class])
class PresenterModule constructor(private val view: CategoriesView) {

    @PresenterScope
    @Provides
    internal fun provideView(): CategoriesView = view

    @Module
    interface Declaration {

        @PresenterScope
        @Binds
        fun bindCategoriesPresenter(categoriesPresenterImpl: CategoriesPresenterImpl): CategoriesPresenter
    }
}