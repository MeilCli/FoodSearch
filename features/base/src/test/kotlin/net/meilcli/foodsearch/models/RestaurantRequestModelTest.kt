package net.meilcli.foodsearch.models

import net.meilcli.foodsearch.api.gnavi.RestaurantRequestQuery
import net.meilcli.foodsearch.api.gnavi.entities.*
import org.junit.Test

class RestaurantRequestModelTest {

    @Test
    fun testArea() {
        val prefectureArea = Area(
            name = "prefectureName",
            code = "prefectureCode"
        )
        val largeArea = LargeArea(
            name = "largeName",
            code = "largeCode",
            prefectureArea = prefectureArea
        )
        val middleArea = MiddleArea(
            name = "middleName",
            code = "middleCode",
            prefectureArea = prefectureArea,
            largeArea = largeArea
        )
        val smallArea = SmallArea(
            name = "smallName",
            code = "smallCode",
            prefectureArea = prefectureArea,
            largeArea = largeArea,
            middleArea = middleArea
        )
        val requestQuery = RestaurantRequestQuery()
        RestaurantRequestModel.Japanese(requestQuery, largeArea, middleArea, smallArea)

        assert(requestQuery.largeArea == "largeCode")
        assert(requestQuery.middleArea == "middleCode")
        assert(requestQuery.smallArea == "smallCode")
    }

    @Test
    fun testCategories() {
        val requestQuery = RestaurantRequestQuery()
        val request = RestaurantRequestModel.Japanese(requestQuery, "").apply {
            categories = arrayOf(
                LargeCategory(
                    name = "largeName",
                    code = "largeCode"
                ),
                SmallCategory(
                    name = "smallName",
                    code = "smallCode",
                    largeCategoryCode = "largeCodeInSmall"
                )
            )
        }

        val largeCategories = checkNotNull(requestQuery.largeCategories)
        val smallCategories = checkNotNull(requestQuery.smallCategories)

        assert(largeCategories.size == 1)
        assert(largeCategories[0] == "largeCode")
        assert(smallCategories.size == 1)
        assert(smallCategories[0] == "smallCode")
    }

}