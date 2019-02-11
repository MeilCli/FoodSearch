package net.meilcli.foodsearch.services

import net.meilcli.foodsearch.api.gnavi.entities.LargeCategory
import org.junit.Test

class JsonServiceTest {

    @Test
    fun testLargeCategories() {
        val largeCategories = listOf(
            LargeCategory("name1", "code1"),
            LargeCategory("name2", "code2")
        )
        val service = JsonService()

        val json = service.toLargeCategoriesJson(largeCategories)
        val result = service.toLargeCategoriesValue(json)

        checkNotNull(result)
        assert(result.size == 2)
        assert(largeCategories[0] == result[0])
        assert(largeCategories[1] == result[1])
    }
}