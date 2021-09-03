/*
package com.russier.laurent.data

import com.russier.laurent.data.database.CategoryDao
import com.russier.laurent.data.database.DbCategory
import com.russier.laurent.domain.Category
import com.russier.laurent.domain.CategoryRepository
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.assertThat
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.mock
import org.mockito.Mockito.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CategoryRepositoryImplSpec : Spek({

    beforeEachTest {
        RxJavaPlugins.initIoScheduler { Schedulers.trampoline() }
    }

    afterEachTest {
        RxJavaPlugins.reset()
    }

    describe("a repository") {
        lateinit var repository: CategoryRepository
        lateinit var categoryService: CategoryService
        lateinit var mapper: CategoryMapper
        lateinit var categoryDao: CategoryDao
        beforeEach {
            categoryService = mock(CategoryService::class.java)
            mapper = mock(CategoryMapper::class.java)
            categoryDao = mock(CategoryDao::class.java)
            repository = CategoryRepositoryImpl(categoryService, mapper, categoryDao)
        }

        describe("no local data") {
            beforeEach {
                given(categoryDao.selectAll()).willReturn(Single.just(emptyList()))
            }

            describe("a category service and a mapper") {
                lateinit var firstCategory: Category
                lateinit var subCategory: Category
                lateinit var resources: List<JsonCategory>
                lateinit var mappedCategories: List<Category>
                beforeEach {
                    firstCategory = Category("123", "cat1")
                    val jsonCategories = mock(JsonCategories::class.java)
                    resources = mock(List::class.java) as List<JsonCategory>
                    given(jsonCategories.resources).willReturn(resources)
                    given(categoryService.fetchCategories()).willReturn(Single.just(jsonCategories))
                    subCategory = Category("555", "subCategory", "parentId")
                    mappedCategories = listOf(
                        firstCategory,
                        Category("456", "cat2"),
                        subCategory
                    )
                    given(mapper.transform(resources)).willReturn(
                        mappedCategories
                    )
                }

                describe("fetching categories") {
                    lateinit var testObserver: TestObserver<List<Category>>
                    beforeEach {
                        testObserver = repository.fetchCategories().test()
                    }

                    it("return categories") {
                        testObserver.awaitTerminalEvent()
                        testObserver.assertComplete()
                        val categories = testObserver.values()[0]
                        assertThat(categories).hasSize(2)
                        assertThat(categories[0]).isEqualTo(firstCategory)
                    }

                    describe("a dao mapper") {
                        val dbCategories = listOf<DbCategory>()
                        beforeEach {
                            given(mapper.transformIntoDb(mappedCategories)).willReturn(dbCategories)
                        }

                        it("should save it to the DAO") {
                            verify(categoryDao).insert(dbCategories)
                        }
                    }
                }

                describe("fetching sub categories") {
                    lateinit var testObserver: TestObserver<List<Category>>
                    beforeEach {
                        testObserver = repository.fetchSubCategories("parentId").test()
                    }

                    it("return sub categories") {
                        testObserver.awaitTerminalEvent()
                        testObserver.assertComplete()
                        val categories = testObserver.values()[0]
                        assertThat(categories).hasSize(1)
                        assertThat(categories[0]).isEqualTo(subCategory)
                    }
                }
            }
        }

        describe("local data") {
            lateinit var dbCategories: List<DbCategory>
            beforeEach {
                dbCategories = listOf(mock(DbCategory::class.java))
                given(categoryDao.selectAll()).willReturn(Single.just(dbCategories))
            }

            describe("a db category mapper") {
                lateinit var localCategories: List<Category>
                beforeEach {
                    localCategories = listOf(mock(Category::class.java))
                    given(mapper.transformFromDb(dbCategories)).willReturn(localCategories)
                }

                describe("fetching categories") {
                    lateinit var testObserver: TestObserver<List<Category>>
                    beforeEach {
                        testObserver = repository.fetchCategories().test()
                    }

                    it("should return mapped local categories") {
                        testObserver.awaitTerminalEvent()
                        testObserver.assertComplete()
                        assertThat(testObserver.values()[0]).isEqualTo(localCategories)
                    }
                }
            }
        }
    }
})*/
