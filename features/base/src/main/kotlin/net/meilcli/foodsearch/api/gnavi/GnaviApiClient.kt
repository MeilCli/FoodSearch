package net.meilcli.foodsearch.api.gnavi

import com.squareup.moshi.Moshi
import net.meilcli.foodsearch.api.ApiClient
import net.meilcli.foodsearch.api.ApiException
import net.meilcli.foodsearch.api.IRequestQuery
import net.meilcli.foodsearch.api.gnavi.entities.*

object GnaviApiClient : ApiClient(), IGnaviApiClient {

    private const val host = "https://api.gnavi.co.jp"
    private const val restaurantSearchPath = "/RestSearchAPI/v3/"
    private const val foreignRestaurantSearchPath = "/ForeignRestSearchAPI/v3/"
    private const val prefectureAreaPath = "/master/PrefSearchAPI/v3/"
    private const val largeAreaPath = "/master/GAreaLargeSearchAPI/v3/"
    private const val middleAreaPath = "/master/GAreaMiddleSearchAPI/v3/"
    private const val smallAreaPath = "/master/GAreaSmallSearchAPI/v3/"
    private const val largeCategoryPath = "/master/CategoryLargeSearchAPI/v3/"
    private const val smallCategoryPath = "/master/CategorySmallSearchAPI/v3/"

    private val moshi = Moshi.Builder().add(GnaviJsonAdapterFactory).build()
    private val errorJsonAdapter by lazy { moshi.adapter(Error::class.java) }

    override suspend fun searchRestaurant(query: RestaurantRequestQuery): RestaurantSearchResponse {
        return callApi("$host$restaurantSearchPath", query)
    }

    override suspend fun searchForeignRestaurant(query: ForeignRestaurantRequestQuery): ForeignRestaurantSearchResponse {
        return callApi("$host$foreignRestaurantSearchPath", query)
    }

    override suspend fun getPrefectureArea(query: AreaRequestQuery): PrefectureAreaResponse {
        return callApi("$host$prefectureAreaPath", query)
    }

    override suspend fun getLargeArea(query: AreaRequestQuery): LargeAreaResponse {
        return callApi("$host$largeAreaPath", query)
    }

    override suspend fun getMiddleArea(query: AreaRequestQuery): MiddleAreaResponse {
        return callApi("$host$middleAreaPath", query)
    }

    override suspend fun getSmallArea(query: AreaRequestQuery): SmallAreaResponse {
        return callApi("$host$smallAreaPath", query)
    }

    override suspend fun getLargeCategories(query: CategoryRequestQuery): LargeCategoryResponse {
        return callApi("$host$largeCategoryPath", query)
    }

    override suspend fun getSmallCategories(query: CategoryRequestQuery): SmallCategoryResponse {
        return callApi("$host$smallCategoryPath", query)
    }

    private suspend inline fun <reified TResponse> callApi(
        url: String,
        query: IRequestQuery
    ): TResponse where TResponse : Any {
        try {
            val response = get(url, query)
            val result = moshi.adapter(TResponse::class.java).fromJson(response)
            checkNotNull(result)
            return result
        } catch (apiException: ApiException) {
            throw apiException.convertToGnaviApiException()
        }
    }

    private fun ApiException.convertToGnaviApiException(): GnaviApiException {
        val error = errorJsonAdapter.fromJson(checkNotNull(this.message))
        checkNotNull(error)
        return GnaviApiException(error)
    }
}