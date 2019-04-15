package com.russier.laurent.injection

import com.russier.laurent.ui.category.CategoryPresenter
import com.russier.laurent.ui.category.CategoryPresenterImpl
import com.russier.laurent.ui.category.CategoryView
import com.russier.laurent.ui.subcategory.SubCategoryPresenter
import com.russier.laurent.ui.subcategory.SubCategoryPresenterImpl
import com.russier.laurent.ui.subcategory.SubCategoryView
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [PresenterModule.Declaration::class])
class PresenterModule {

    private lateinit var view: CategoryView
    private lateinit var subCategoryView: SubCategoryView

    constructor(view: CategoryView) {
        this.view = view
    }

    constructor(subCategoryView: SubCategoryView) {
        this.subCategoryView = subCategoryView
    }

    @PresenterScope
    @Provides
    internal fun provideView(): CategoryView = view

    @PresenterScope
    @Provides
    internal fun provideSubCategoryView(): SubCategoryView = subCategoryView

    @Module
    interface Declaration {

        @PresenterScope
        @Binds
        fun bindCategoryPresenter(presenter: CategoryPresenterImpl): CategoryPresenter

        @PresenterScope
        @Binds
        fun bindSubCategoryPresenter(presenter: SubCategoryPresenterImpl): SubCategoryPresenter
    }
}