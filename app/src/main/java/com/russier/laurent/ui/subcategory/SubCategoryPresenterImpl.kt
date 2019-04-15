package com.russier.laurent.ui.subcategory

import com.russier.laurent.domain.CategoryUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SubCategoryPresenterImpl @Inject constructor(
    private val useCase: CategoryUseCase,
    private val view: SubCategoryView
) : SubCategoryPresenter {

    private val disposable = CompositeDisposable()

    override fun onViewReady(categoryId: String) {
        disposable.add(
            useCase
                .fetchSubCategories(categoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { view.displaySubCategories(it) },
                    { view.displayError() }
                )
        )
    }

    override fun onDestroy() {
        disposable.clear()
    }
}