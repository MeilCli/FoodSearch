package net.meilcli.foodsearch.services

import net.meilcli.foodsearch.Language
import net.meilcli.foodsearch.api.gnavi.*
import net.meilcli.foodsearch.api.gnavi.entities.*
import net.meilcli.foodsearch.models.RestaurantRequestModel

class GnaviService(private val configurationService: IConfigurationService) : IGnaviService {

    private var largeCategoriesCache: List<LargeCategory>? = null
    private var smallCategoriesCache: List<SmallCategory>? = null
    private var prefectureAreaCache: List<PrefectureArea>? = null
    private var largeAreaCache: List<LargeArea>? = null
    private var middleAreaCache: List<MiddleArea>? = null
    private var smallAreaCache: List<SmallArea>? = null

    override fun createRestaurantRequestModel(id: String): RestaurantRequestModel {
        return when (val language = configurationService.currentLanguage) {
            Language.Japanese -> RestaurantRequestModel.Japanese(RestaurantRequestQuery(), id)
            Language.English -> RestaurantRequestModel.Foreign(ForeignRestaurantRequestQuery(), id, language)
        }
    }

    override fun createRestaurantRequestModel(ids: Array<String>): RestaurantRequestModel {
        return when (val language = configurationService.currentLanguage) {
            Language.Japanese -> RestaurantRequestModel.Japanese(RestaurantRequestQuery(), ids)
            Language.English -> RestaurantRequestModel.Foreign(ForeignRestaurantRequestQuery(), ids, language)
        }
    }

    override fun createRestaurantRequestModel(
        largeArea: LargeArea,
        middleArea: MiddleArea,
        smallArea: SmallArea
    ): RestaurantRequestModel {
        return when (val language = configurationService.currentLanguage) {
            Language.Japanese -> RestaurantRequestModel.Japanese(
                RestaurantRequestQuery(),
                largeArea,
                middleArea,
                smallArea
            )
            Language.English -> RestaurantRequestModel.Foreign(
                ForeignRestaurantRequestQuery(),
                largeArea,
                middleArea,
                smallArea,
                language
            )
        }
    }

    override fun createRestaurantRequestModel(latitude: Double, longitude: Double): RestaurantRequestModel {
        return when (val language = configurationService.currentLanguage) {
            Language.Japanese -> RestaurantRequestModel.Japanese(
                RestaurantRequestQuery(),
                latitude = latitude,
                longitude = longitude
            )
            Language.English -> RestaurantRequestModel.Foreign(
                ForeignRestaurantRequestQuery(),
                latitude = latitude,
                longitude = longitude,
                language = language
            )
        }
    }

    override suspend fun searchRestaurant(requestModel: RestaurantRequestModel): ISearchResponse<IRestaurant> {
        return when (requestModel) {
            is RestaurantRequestModel.Japanese -> GnaviApiClient.searchRestaurant(requestModel.requestQuery)
            is RestaurantRequestModel.Foreign -> GnaviApiClient.searchForeignRestaurant(requestModel.requestQuery)
        }
    }

    private fun createCategoryRequestQuery(): CategoryRequestQuery {
        return CategoryRequestQuery().apply {
            language = configurationService.currentLanguage.toGnaviLanguage()
        }
    }

    private fun createAreaRequestQuery(): AreaRequestQuery {
        return AreaRequestQuery().apply {
            language = configurationService.currentLanguage.toGnaviLanguage()
        }
    }

    override suspend fun getLargeCategories(): List<LargeCategory> {
        val largeCategories =
            largeCategoriesCache ?: GnaviApiClient.getLargeCategories(createCategoryRequestQuery()).largeCategories
        largeCategoriesCache = largeCategories
        return largeCategories
    }

    override suspend fun getSmallCategories(): List<SmallCategory> {
        val smallCategories =
            smallCategoriesCache ?: GnaviApiClient.getSmallCategories(createCategoryRequestQuery()).smallCategories
        smallCategoriesCache = smallCategories
        return smallCategories
    }

    override suspend fun getPrefectureArea(): List<PrefectureArea> {
        val prefectureArea =
            prefectureAreaCache ?: GnaviApiClient.getPrefectureArea(createAreaRequestQuery()).prefectureArea
        prefectureAreaCache = prefectureArea
        return prefectureArea
    }

    override suspend fun getLargeArea(prefectureArea: PrefectureArea): List<LargeArea> {
        val largeArea = largeAreaCache ?: GnaviApiClient.getLargeArea(createAreaRequestQuery()).largeArea
        largeAreaCache = largeArea
        return largeArea.filter { it.prefectureArea.code == prefectureArea.code }
    }

    override suspend fun getMiddleArea(largeArea: LargeArea): List<MiddleArea> {
        val middleArea = middleAreaCache ?: GnaviApiClient.getMiddleArea(createAreaRequestQuery()).middleArea
        middleAreaCache = middleArea
        return middleArea.filter { it.largeArea.code == largeArea.code }
    }

    override suspend fun getSmallArea(middleArea: MiddleArea): List<SmallArea> {
        val smallArea = smallAreaCache ?: GnaviApiClient.getSmallArea(createAreaRequestQuery()).smallArea
        smallAreaCache = smallArea
        return smallArea.filter { it.middleArea.code == middleArea.code }
    }
}