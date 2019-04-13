package com.russier.laurent.domain

import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CategoryUseCaseImplSpec : Spek({

    describe("a use case") {
        lateinit var useCase: CategoryUseCaseImpl
        lateinit var repository: CategoryRepository
        beforeEach {
            repository = mock(CategoryRepository::class.java)
            useCase = CategoryUseCaseImpl(repository)
        }

        describe("categories") {
            lateinit var givenCategories: List<Category>
            beforeEach {
                givenCategories = listOf(mock(Category::class.java))
                given(repository.fetchCategories()).willReturn(Single.just(givenCategories))
            }

            describe("fetch categories") {
                lateinit var categories: List<Category>
                beforeEach {
                    categories = useCase.fetchCategories().test().values()[0]
                }

                it("should fetch categories") {
                    assertThat(categories).isEqualTo(givenCategories)
                }
            }
        }

        describe("sub categories") {
            lateinit var givenSubCategories: List<Category>
            beforeEach {
                givenSubCategories = listOf(mock(Category::class.java))
                given(repository.fetchSubCategories("123")).willReturn(Single.just(givenSubCategories))
            }

            describe("fetch sub categories") {
                lateinit var subCategories: List<Category>
                beforeEach {
                    subCategories = useCase.fetchSubCategories("123").test().values()[0]
                }

                it("should fetch categories") {
                    assertThat(subCategories).isEqualTo(givenSubCategories)
                }
            }
        }
    }
})