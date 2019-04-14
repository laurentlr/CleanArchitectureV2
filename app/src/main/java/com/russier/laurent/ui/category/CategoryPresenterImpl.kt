package com.russier.laurent.ui.category

import com.russier.laurent.domain.CategoryUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CategoryPresenterImpl @Inject constructor(
    private val useCase: CategoryUseCase,
    private val view: CategoryView
) : CategoryPresenter {

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