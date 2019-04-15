package com.russier.laurent.data

import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CategoryServiceSpec : Spek({

    describe("a category service") {
        lateinit var categoryService: BankinMockService<CategoryService>

        beforeEach {
            categoryService = BankinMockService(CategoryService::class.java)
            categoryService.init()
        }

        afterEach {
            categoryService.destroy()
        }

        describe("fetch categories") {
            lateinit var testObserver: TestObserver<JsonCategories>
            beforeEach {
                categoryService.enqueueResponse(200, "category/categories.json")
                testObserver = categoryService.get().fetchCategories().test()
            }

            it("should deserialize the payload") {
                testObserver.awaitTerminalEvent()
                testObserver.assertComplete()

                val categories = testObserver.values()[0].resources
                assertThat(categories).hasSize(142)
                assertThat(categories[0].id).isEqualTo(441988)
                assertThat(categories[0].name).isEqualTo("TVA")
                assertThat(categories[0].parent!!.id).isEqualTo(159)

                assertThat(categories.find { it.id == 315 }!!.parent).isNull()
            }
        }
    }
})