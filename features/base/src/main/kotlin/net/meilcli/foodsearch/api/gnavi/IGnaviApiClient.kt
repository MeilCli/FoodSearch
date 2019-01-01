package net.meilcli.foodsearch.api.gnavi

import net.meilcli.foodsearch.api.IApiClient
import net.meilcli.foodsearch.api.gnavi.entities.*

interface IGnaviApiClient : IApiClient {

    suspend fun searchRestaurant(query: RestaurantRequestQuery): RestaurantSearchResponse

    suspend fun searchForeignRestaurant(query: ForeignRestaurantRequestQuery): ForeignRestaurantSearchResponse

    suspend fun getPrefectureArea(query: AreaRequestQuery): PrefectureAreaResponse

    suspend fun getLargeArea(query: AreaRequestQuery): LargeAreaResponse

    suspend fun getMiddleArea(query: AreaRequestQuery): MiddleAreaResponse

    suspend fun getSmallArea(query: AreaRequestQuery): SmallAreaResponse

    suspend fun getLargeCategories(query: CategoryRequestQuery): LargeCategoryResponse

    suspend fun getSmallCategories(query: CategoryRequestQuery): SmallCategoryResponse
}