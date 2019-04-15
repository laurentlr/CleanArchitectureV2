package com.russier.laurent.ui.subcategory

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

object SubCategoryPresenterImplSpec : Spek({

    beforeEachTest {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    afterEachTest {
        RxAndroidPlugins.reset()
    }

    describe("a presenter") {
        lateinit var presenter: SubCategoryPresenter
        lateinit var userCase: CategoryUseCase
        lateinit var view: SubCategoryView
        beforeEach {
            userCase = mock(CategoryUseCase::class.java)
            view = mock(SubCategoryView::class.java)
            presenter = SubCategoryPresenterImpl(userCase, view)
        }

        describe("a success subcategory call") {
            lateinit var subcategories: List<Category>
            beforeEach {
                subcategories = listOf(mock(Category::class.java))
                given(userCase.fetchSubCategories("123")).willReturn(Single.just(subcategories))
            }

            describe("on view ready") {
                beforeEach {
                    presenter.onViewReady("123")
                }

                it("display subcategories") {
                    verify(view).displaySubCategories(subcategories)
                }
            }
        }

        describe("an erre subcategory call") {
            beforeEach {
                given(userCase.fetchSubCategories("123")).willReturn(Single.error(Exception()))
            }

            describe("on view ready") {
                beforeEach {
                    presenter.onViewReady("123")
                }

                it("should display error") {
                    verify(view).displayError()
                }
            }
        }
    }
})