package com.russier.laurent.ui

import com.russier.laurent.domain.CategoryUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class CategoriesPresenterImpl(
    private val useCase: CategoryUseCase,
    private val view: CategoriesView
) : CategoriesPresenter {

    private val disposable = CompositeDisposable()

    override fun onViewReady() {
        disposable.add(
            useCase
                .fetchCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { view.displayCategories(it) },
                    { view.displayError() }
                )
        )
    }

    override fun onDestroy() {
        disposable.clear()
    }
}