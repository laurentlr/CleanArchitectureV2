package com.russier.laurent.data

import com.russier.laurent.domain.Category
import com.russier.laurent.domain.CategoryRepository
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions.assertThat
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CategoryRepositoryImplSpec : Spek({

    describe("a repository") {
        lateinit var repository: CategoryRepository
        lateinit var categoryService: CategoryService
        lateinit var mapper: CategoryMapper
        beforeEach {
            categoryService = mock(CategoryService::class.java)
            mapper = mock(CategoryMapper::class.java)
            repository = CategoryRepositoryImpl(categoryService, mapper)
        }

        describe("a category service and a mapper") {
            lateinit var firstCategory: Category
            lateinit var subCategory: Category
            beforeEach {
                firstCategory = Category("123", "cat1")
                val jsonCategories = mock(JsonCategories::class.java)
                val resources = mock(List::class.java) as List<JsonCategory>
                given(jsonCategories.resources).willReturn(resources)
                given(categoryService.fetchCategories()).willReturn(Single.just(jsonCategories))
                subCategory = Category("555", "subCategory", "parentId")
                given(mapper.transform(resources)).willReturn(
                    listOf(
                        firstCategory,
                        Category("456", "cat2"),
                        subCategory
                    )
                )
            }

            describe("fetching categories") {
                lateinit var testObserver: TestObserver<List<Category>>
                beforeEach {
                    testObserver = repository.fetchCategories().test()
                }

                it("return categories") {
                    testObserver.assertComplete()
                    val categories = testObserver.values()[0]
                    assertThat(categories).hasSize(2)
                    assertThat(categories[0]).isEqualTo(firstCategory)
                }
            }

            describe("fetching sub categories") {
                lateinit var testObserver: TestObserver<List<Category>>
                beforeEach {
                    testObserver = repository.fetchSubCategories("parentId").test()
                }

                it("return sub categories") {
                    testObserver.assertComplete()
                    val categories = testObserver.values()[0]
                    assertThat(categories).hasSize(1)
                    assertThat(categories[0]).isEqualTo(subCategory)
                }
            }
        }
    }
})