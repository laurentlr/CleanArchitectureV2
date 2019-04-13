package com.russier.laurent.ui

import com.russier.laurent.domain.Category
import com.russier.laurent.domain.CategoryUseCase
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CategoriesPresenterImplSpec : Spek({

    beforeEachTest {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    afterEachTest {
        RxAndroidPlugins.reset()
    }

    describe("a presenter") {
        lateinit var useCase: CategoryUseCase
        lateinit var view: CategoriesView
        lateinit var presenter: CategoriesPresenterImpl
        beforeEach {
            useCase = mock(CategoryUseCase::class.java)
            view = mock(CategoriesView::class.java)
            presenter = CategoriesPresenterImpl(useCase, view)
        }

        describe("categories") {
            lateinit var categories: List<Category>
            beforeEach {
                categories = listOf(mock(Category::class.java))
                given(useCase.fetchCategories()).willReturn(Single.just(categories))
            }

            describe("on view ready") {
                beforeEach {
                    presenter.onViewReady()
                }

                it("should display categories") {
                    verify(view).displayCategories(categories)
                }
            }
        }

        describe("an error fetching categories") {
            beforeEach {
                given(useCase.fetchCategories()).willReturn(Single.error(Exception()))
            }

            describe("on view ready") {
                beforeEach {
                    presenter.onViewReady()
                }

                it("should display error") {
                    verify(view).displayError()
                }
            }
        }
    }
})