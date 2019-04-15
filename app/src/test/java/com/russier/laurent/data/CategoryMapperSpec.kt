package com.russier.laurent.data

import com.russier.laurent.domain.Category
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object CategoryMapperSpec : Spek({

    describe("a mapper") {
        lateinit var mapper: CategoryMapper
        beforeEach {
            mapper = CategoryMapper()
        }

        describe("a list of json category") {
            lateinit var jsonCategories: List<JsonCategory>
            beforeEach {
                jsonCategories = listOf(
                    JsonCategory(123, "cat1", null),
                    JsonCategory(456, "cat2", JsonParent(444))
                )
            }

            describe("transform") {
                lateinit var categories: List<Category>
                beforeEach {
                    categories = mapper.transform(jsonCategories)
                }

                it("should map json item to domain item") {
                    assertThat(categories).hasSize(2)
                    assertThat(categories[0].id).isEqualTo("123")
                    assertThat(categories[0].name).isEqualTo("cat1")
                    assertThat(categories[0].parentId).isNull()
                    assertThat(categories[1].id).isEqualTo("456")
                    assertThat(categories[1].name).isEqualTo("cat2")
                    assertThat(categories[1].parentId).isEqualTo("444")
                }
            }
        }
    }
})