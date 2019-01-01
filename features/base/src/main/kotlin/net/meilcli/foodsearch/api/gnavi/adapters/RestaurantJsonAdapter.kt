package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.*
import net.meilcli.foodsearch.IJsonAdapterFactory
import net.meilcli.foodsearch.api.createNonNullJsonDateException
import net.meilcli.foodsearch.api.gnavi.entities.*
import net.meilcli.foodsearch.extensions.letNullIfEmpty
import java.lang.reflect.Type
import java.util.*
import net.meilcli.foodsearch.api.gnavi.adapters.RestaurantJsonAdapter_ApiAccessJsonAdapter as ApiAccessJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.RestaurantJsonAdapter_ApiCodeJsonAdapter as ApiCodeJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.RestaurantJsonAdapter_ApiCouponJsonAdapter as ApiCouponJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.RestaurantJsonAdapter_ApiImageJsonAdapter as ApiImageJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.RestaurantJsonAdapter_ApiRestaurantJsonAdapter as ApiRestaurantJsonAdapter

internal class RestaurantJsonAdapter(moshi: Moshi) : JsonAdapter<Restaurant>() {

    // https://api.gnavi.co.jp/api/manual/restsearch/

    private val apiRestaurantJsonAdapter = moshi.adapter(ApiRestaurant::class.java)

    override fun fromJson(reader: JsonReader): Restaurant {
        val apiRestaurant = apiRestaurantJsonAdapter.fromJson(reader) ?: throw createNonNullJsonDateException(reader)

        val smallArea = Area(apiRestaurant.code.smallAreaName, apiRestaurant.code.smallAreaCode)
        val largeCategories = createCategories(
            apiRestaurant.code.largeCategoryNames,
            apiRestaurant.code.largeCategoryCodes
        )
        val smallCategories = createCategories(
            apiRestaurant.code.smallCategoryNames,
            apiRestaurant.code.smallCategoryCodes
        )
        val contact = Contact(apiRestaurant.address, apiRestaurant.phoneNumber)
        val image = Image(
            thumbnailUrl1 = apiRestaurant.image.thumbnailUrl1.letNullIfEmpty(),
            thumbnailUrl2 = apiRestaurant.image.thumbnailUrl2.letNullIfEmpty(),
            qrCodeUrl = apiRestaurant.image.qrCodeUrl.letNullIfEmpty()
        )
        val location = Location(
            latitude = apiRestaurant.latitude,
            longitude = apiRestaurant.longitude
        )
        val publicRelation = apiRestaurant.publicRelation
        val access = createAccess(apiRestaurant.access)

        return Restaurant(
            id = apiRestaurant.id,
            updateAt = apiRestaurant.updateAt,
            name = apiRestaurant.name,
            nameOfReading = apiRestaurant.nameOfReading,
            location = location,
            smallArea = smallArea,
            largeCategories = largeCategories,
            smallCategories = smallCategories,
            url = apiRestaurant.url.letNullIfEmpty(),
            urlForMobile = apiRestaurant.urlForMobile.letNullIfEmpty(),
            couponUrl = apiRestaurant.coupon.pc.letNullIfEmpty(),
            couponUrlForMobile = apiRestaurant.coupon.mobile.letNullIfEmpty(),
            image = image,
            openTime = apiRestaurant.openTime,
            holiday = apiRestaurant.holiday,
            access = access,
            contact = contact,
            publicRelation = publicRelation,
            averageBudget = apiRestaurant.averageBudget,
            creditCard = apiRestaurant.creditCard.letNullIfEmpty(),
            electronicMoney = apiRestaurant.electronicMoney.letNullIfEmpty()
        )
    }

    private fun createCategories(names: Array<String>, codes: Array<String>): Array<ICategory> {
        return names
            .asSequence()
            .filter { it.isNotEmpty() }
            .zip(
                codes
                    .asSequence()
                    .filter { it.isNotEmpty() }
            )
            .map { Category(it.first, it.second) as ICategory }
            .toList()
            .toTypedArray()
    }

    private fun createAccess(access: ApiAccess): String {
        val time = "${if (access.time?.contains('車') == false) "徒歩" else ""}${access.time}分"
        return "${access.line}${access.station}${access.stationExit} $time ${access.note}".trim(' ')
    }

    override fun toJson(writer: JsonWriter, value: Restaurant?) {
        TODO("not implemented")
    }

    internal object InternalJsonAdapterFactory : IJsonAdapterFactory {

        override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
            return when (type) {
                ApiRestaurant::class.java -> ApiRestaurantJsonAdapter(moshi)
                ApiCoupon::class.java -> ApiCouponJsonAdapter(moshi)
                ApiImage::class.java -> ApiImageJsonAdapter(moshi)
                ApiAccess::class.java -> ApiAccessJsonAdapter(moshi)
                ApiCode::class.java -> ApiCodeJsonAdapter(moshi)
                else -> null
            }
        }
    }

    @JsonClass(generateAdapter = true)
    internal data class ApiRestaurant(
        val id: String,

        @Json(name = "update_date")
        val updateAt: Date,

        val name: String,

        @Json(name = "name_kana")
        val nameOfReading: String,

        val latitude: Double,
        val longitude: Double,
        val url: String?,

        @Json(name = "url_mobile")
        val urlForMobile: String?,

        @Json(name = "coupon_url")
        val coupon: ApiCoupon,

        @Json(name = "image_url")
        val image: ApiImage,

        val address: String,

        @Json(name = "tel")
        val phoneNumber: String,

        @Json(name = "opentime")
        val openTime: String,

        val holiday: String,
        val access: ApiAccess,

        @Json(name = "pr")
        val publicRelation: PublicRelation,

        val code: ApiCode,

        @Json(name = "budget")
        val averageBudget: Int,

        @Json(name = "credit_card")
        val creditCard: String?,

        @Json(name = "e_money")
        val electronicMoney: String?
    )

    @JsonClass(generateAdapter = true)
    internal data class ApiCoupon(val pc: String?, val mobile: String?)

    @JsonClass(generateAdapter = true)
    internal data class ApiImage(
        @Json(name = "shop_image1")
        val thumbnailUrl1: String?,

        @Json(name = "shop_image2")
        val thumbnailUrl2: String?,

        @Json(name = "qrcode")
        val qrCodeUrl: String?
    )

    @JsonClass(generateAdapter = true)
    internal data class ApiAccess(
        val line: String?,
        val station: String?,

        @Json(name = "station_exit")
        val stationExit: String?,

        @Json(name = "walk")
        val time: String?,

        val note: String?
    )

    @JsonClass(generateAdapter = true)
    internal data class ApiCode(
        @Json(name = "areacode_s")
        val smallAreaCode: String?,

        @Json(name = "areaname_s")
        val smallAreaName: String,

        @Json(name = "category_code_l")
        val largeCategoryCodes: Array<String>,

        @Json(name = "category_name_l")
        val largeCategoryNames: Array<String>,

        @Json(name = "category_code_s")
        val smallCategoryCodes: Array<String>,

        @Json(name = "category_name_s")
        val smallCategoryNames: Array<String>
    )
}