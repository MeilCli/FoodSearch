package net.meilcli.foodsearch.services

import net.meilcli.foodsearch.api.gnavi.entities.*
import net.meilcli.foodsearch.models.RestaurantRequestModel

interface IGnaviService {

    fun createRestaurantRequestModel(): RestaurantRequestModel

    fun createRestaurantRequestModel(id: String): RestaurantRequestModel

    fun createRestaurantRequestModel(ids: Array<String>): RestaurantRequestModel

    fun createRestaurantRequestModel(
        largeArea: LargeArea,
        middleArea: MiddleArea,
        smallArea: SmallArea
    ): RestaurantRequestModel

    fun createRestaurantRequestModel(latitude: Double, longitude: Double): RestaurantRequestModel

    suspend fun searchRestaurant(requestModel: RestaurantRequestModel): ISearchResponse<IRestaurant>

    suspend fun getLargeCategories(): List<LargeCategory>

    suspend fun getSmallCategories(): List<SmallCategory>

    suspend fun getPrefectureArea(): List<PrefectureArea>

    suspend fun getLargeArea(prefectureArea: PrefectureArea): List<LargeArea>

    suspend fun getMiddleArea(largeArea: LargeArea): List<MiddleArea>

    suspend fun getSmallArea(middleArea: MiddleArea): List<SmallArea>
}