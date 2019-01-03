package net.meilcli.foodsearch.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import net.meilcli.foodsearch.Language
import net.meilcli.foodsearch.R
import net.meilcli.foodsearch.api.gnavi.ForeignRestaurantRequestQuery
import net.meilcli.foodsearch.api.gnavi.IRestaurantRequestQuery
import net.meilcli.foodsearch.api.gnavi.Range
import net.meilcli.foodsearch.api.gnavi.RestaurantRequestQuery
import net.meilcli.foodsearch.api.gnavi.entities.*
import kotlin.reflect.KMutableProperty0

sealed class RestaurantRequestModel {

    companion object {

        private const val defaultCount = 20
        private const val defaultPage = 1

        private val defaultRange = Range.M500
    }

    private val requestQuery: IRestaurantRequestQuery

    var categories: Array<ICategory>? = null
        set(value) {
            field = value
            requestQuery.largeCategories = convertCategoryCodes<LargeCategory>(value)
            requestQuery.smallCategories = convertCategoryCodes<SmallCategory>(value)
        }

    var range: Range = defaultRange
        set(value) {
            field = value
            requestQuery.range = value
        }

    var page: Int = defaultPage
        set(value) {
            field = value
            requestQuery.page = value
        }

    var searchWords: Array<String>? = null
        set(value) {
            field = value
            requestQuery.searchWords = value
        }

    constructor(requestQuery: IRestaurantRequestQuery, id: String) : this(requestQuery, arrayOf(id))

    constructor(requestQuery: IRestaurantRequestQuery, ids: Array<String>) {
        this.requestQuery = requestQuery
        requestQuery.count = defaultCount
        requestQuery.ids = ids
    }

    constructor(
        requestQuery: IRestaurantRequestQuery,
        largeArea: LargeArea,
        middleArea: MiddleArea,
        smallArea: SmallArea
    ) {
        this.requestQuery = requestQuery
        requestQuery.count = defaultCount
        requestQuery.largeArea = largeArea.code
        requestQuery.middleArea = middleArea.code
        requestQuery.smallArea = smallArea.code
    }

    constructor(requestQuery: IRestaurantRequestQuery, latitude: Double, longitude: Double) {
        this.requestQuery = requestQuery
        requestQuery.count = defaultCount
        requestQuery.latitude = latitude
        requestQuery.longitude = longitude
    }

    private inline fun <reified T> convertCategoryCodes(categories: Array<ICategory>?): Array<String>? where T : ICategory {
        return categories?.asSequence()
            ?.filterIsInstance<T>()
            ?.map { it.code }
            ?.filterNotNull()
            ?.toList()
            ?.toTypedArray()
    }

    abstract fun createOptions(): List<Option>

    class Option(val property: KMutableProperty0<Boolean>, @StringRes val description: Int, @DrawableRes val icon: Int?) {

        var value: Boolean
            get() = property.get()
            set(value) {
                property.set(value)
            }
    }

    class Japanese : RestaurantRequestModel {

        val requestQuery: RestaurantRequestQuery

        constructor(requestQuery: RestaurantRequestQuery, id: String) : super(requestQuery, id) {
            this.requestQuery = requestQuery
        }

        constructor(requestQuery: RestaurantRequestQuery, ids: Array<String>) : super(requestQuery, ids) {
            this.requestQuery = requestQuery
        }

        constructor(
            requestQuery: RestaurantRequestQuery,
            largeArea: LargeArea,
            middleArea: MiddleArea,
            smallArea: SmallArea
        ) : super(requestQuery, largeArea, middleArea, smallArea) {
            this.requestQuery = requestQuery
        }

        constructor(
            requestQuery: RestaurantRequestQuery,
            latitude: Double,
            longitude: Double
        ) : super(requestQuery, latitude = latitude, longitude = longitude) {
            this.requestQuery = requestQuery
        }

        override fun createOptions(): List<Option> {
            return listOf(
                Option(requestQuery::canLunch, R.string.option_canLunch, null),
                Option(requestQuery::canDrinkAll, R.string.option_canDrinkAll, null),
                Option(requestQuery::canEatAll, R.string.option_canEatAll, null),
                Option(requestQuery::hasLunchDesert, R.string.option_hasLunchDesert, null),
                Option(requestQuery::canNoSmoking, R.string.option_canNoSmoking, null),
                Option(requestQuery::canCreditCard, R.string.option_canCreditCard, null),
                Option(requestQuery::canElectronicMoney, R.string.option_canElectronicMoney, null),
                Option(requestQuery::canDelivery, R.string.option_canDelivery, null),
                Option(requestQuery::canReserveInWeb, R.string.option_canReserveInWeb, null),
                Option(requestQuery::isMidnightOpen, R.string.option_isMidnightOpen, null),
                Option(requestQuery::isUntilMorningOpen, R.string.option_isUntilMorningOpen, null),
                Option(requestQuery::hasPrivateRoom, R.string.option_hasPrivateRoom, null),
                Option(requestQuery::hasParking, R.string.option_hasParking, null),
                Option(requestQuery::hasPowerSupply, R.string.option_hasPowerSupply, null),
                Option(requestQuery::hasWifi, R.string.option_hasWifi, null),
                Option(requestQuery::canPet, R.string.option_canPet, null),
                Option(requestQuery::canSeeSports, R.string.option_canSeeSports, null),
                Option(requestQuery::hasDarts, R.string.option_hasDarts, null)
            )
        }
    }

    class Foreign : RestaurantRequestModel {

        val requestQuery: ForeignRestaurantRequestQuery

        constructor(
            requestQuery: ForeignRestaurantRequestQuery,
            id: String,
            language: Language
        ) : super(requestQuery, id) {
            this.requestQuery = requestQuery
            requestQuery.language = language.toGnaviLanguage()
        }

        constructor(
            requestQuery: ForeignRestaurantRequestQuery,
            ids: Array<String>,
            language: Language
        ) : super(requestQuery, ids) {
            this.requestQuery = requestQuery
            requestQuery.language = language.toGnaviLanguage()
        }

        constructor(
            requestQuery: ForeignRestaurantRequestQuery,
            largeArea: LargeArea,
            middleArea: MiddleArea,
            smallArea: SmallArea,
            language: Language
        ) : super(requestQuery, largeArea, middleArea, smallArea) {
            this.requestQuery = requestQuery
            requestQuery.language = language.toGnaviLanguage()
        }

        constructor(
            requestQuery: ForeignRestaurantRequestQuery,
            latitude: Double,
            longitude: Double,
            language: Language
        ) : super(requestQuery, latitude = latitude, longitude = longitude) {
            this.requestQuery = requestQuery
            requestQuery.language = language.toGnaviLanguage()
        }

        override fun createOptions(): List<Option> {
            return listOf(
                Option(requestQuery::hasEnglishSpeakingStaff, R.string.option_hasEnglishSpeakingStaff, null),
                Option(requestQuery::hasEnglishMenu, R.string.option_hasEnglishMenu, null),
                Option(requestQuery::hasVegetarianMenu, R.string.option_hasVegetarianMenu, null),
                Option(requestQuery::hasReligiousMenu, R.string.option_hasReligiousMenu, null),
                Option(requestQuery::canCreditCard, R.string.option_canCreditCard, null),
                Option(requestQuery::hasPrivateRoom, R.string.option_hasPrivateRoom, null),
                Option(requestQuery::canNoSmoking, R.string.option_canNoSmoking, null),
                Option(requestQuery::hasWifi, R.string.option_hasWifi, null)
            )
        }
    }
}