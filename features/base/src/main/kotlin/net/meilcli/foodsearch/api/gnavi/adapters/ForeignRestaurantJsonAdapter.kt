package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.*
import net.meilcli.foodsearch.IJsonAdapterFactory
import net.meilcli.foodsearch.api.createNonNullJsonDateException
import net.meilcli.foodsearch.api.gnavi.entities.*
import net.meilcli.foodsearch.extensions.letNullIfEmpty
import java.lang.reflect.Type
import java.util.*
import net.meilcli.foodsearch.api.gnavi.adapters.ForeignRestaurantJsonAdapter_ApiAreaJsonAdapter as ApiAreaJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.ForeignRestaurantJsonAdapter_ApiCategoriesJsonAdapter as ApiCategoriesJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.ForeignRestaurantJsonAdapter_ApiContactJsonAdapter as ApiContactJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.ForeignRestaurantJsonAdapter_ApiForeignRestaurantJsonAdapter as ApiForeignRestaurantJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.ForeignRestaurantJsonAdapter_ApiImageJsonAdapter as ApiImageJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.ForeignRestaurantJsonAdapter_ApiLocationJsonAdapter as ApiLocationJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.ForeignRestaurantJsonAdapter_ApiNameJsonAdapter as ApiNameJsonAdapter

internal class ForeignRestaurantJsonAdapter(moshi: Moshi) : JsonAdapter<ForeignRestaurant>() {

    // https://api.gnavi.co.jp/api/manual/foreignrestsearch/

    private val apiForeignRestaurantJsonAdapter = moshi.adapter(ApiForeignRestaurant::class.java)

    override fun fromJson(reader: JsonReader): ForeignRestaurant? {
        val apiForeignRestaurant = apiForeignRestaurantJsonAdapter.fromJson(reader)
            ?: throw createNonNullJsonDateException(reader)

        val nameOfReading = apiForeignRestaurant.name.nameOfReading ?: apiForeignRestaurant.name.subName ?: ""
        val smallArea = Area(apiForeignRestaurant.location.area.smallAreaName, null)
        val largeCategories = createCategories(apiForeignRestaurant.categories.largeCategoryNames)
        val smallCategories = createCategories(apiForeignRestaurant.categories.smallCategoryNames)
        val contact = Contact(apiForeignRestaurant.contact.address, apiForeignRestaurant.contact.phoneNumber)
        val image = Image(
            apiForeignRestaurant.image.thumbnailUrl.letNullIfEmpty(),
            null,
            apiForeignRestaurant.image.qrCodeUrl.letNullIfEmpty()
        )
        val location = Location(
            longitude = apiForeignRestaurant.location.longitude,
            latitude = apiForeignRestaurant.location.latitude
        )
        val publicRelation = apiForeignRestaurant.publicRelation

        return ForeignRestaurant(
            id = apiForeignRestaurant.id,
            updateAt = apiForeignRestaurant.updateAt,
            name = apiForeignRestaurant.name.name,
            nameOfReading = nameOfReading,
            location = location,
            smallArea = smallArea,
            largeCategories = largeCategories,
            smallCategories = smallCategories,
            url = apiForeignRestaurant.url.letNullIfEmpty(),
            urlForMobile = apiForeignRestaurant.urlForMobile.letNullIfEmpty(),
            couponUrl = null,
            couponUrlForMobile = null,
            image = image,
            openTime = apiForeignRestaurant.openTime,
            holiday = apiForeignRestaurant.holiday,
            access = apiForeignRestaurant.access,
            contact = contact,
            publicRelation = publicRelation,
            averageBudget = apiForeignRestaurant.averageBudget,
            creditCard = apiForeignRestaurant.creditCard.letNullIfEmpty(),
            electronicMoney = null
        )
    }

    private fun createCategories(names: Array<String>): Array<ICategory> {
        return names.asSequence()
            .filter { it.isNotEmpty() }
            .map { Category(it, null) as ICategory }
            .toList()
            .toTypedArray()
    }

    override fun toJson(writer: JsonWriter, value: ForeignRestaurant?) {
        TODO("not implemented")
    }

    internal object InternalJsonAdapterFactory : IJsonAdapterFactory {

        override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
            return when (type) {
                ApiForeignRestaurant::class.java -> ApiForeignRestaurantJsonAdapter(moshi)
                ApiName::class.java -> ApiNameJsonAdapter(moshi)
                ApiContact::class.java -> ApiContactJsonAdapter(moshi)
                ApiCategories::class.java -> ApiCategoriesJsonAdapter(moshi)
                ApiLocation::class.java -> ApiLocationJsonAdapter(moshi)
                ApiArea::class.java -> ApiAreaJsonAdapter(moshi)
                ApiImage::class.java -> ApiImageJsonAdapter(moshi)
                else -> return null
            }
        }
    }

    @JsonClass(generateAdapter = true)
    internal data class ApiForeignRestaurant(

        val id: String,

        @Json(name = "update_date")
        val updateAt: Date,

        val name: ApiName,

        @Json(name = "business_hour")
        val openTime: String,

        val holiday: String,

        @Json(name = "contacts")
        val contact: ApiContact,

        @Json(name = "sales_points")
        val publicRelation: PublicRelation,

        val access: String,

        @Json(name = "budget")
        val averageBudget: Int,

        @Json(name = "credit_card")
        val creditCard: String,

        val categories: ApiCategories,

        val location: ApiLocation,

        val url: String?,

        @Json(name = "url_mobile")
        val urlForMobile: String?,

        @Json(name = "image_url")
        val image: ApiImage
    )

    @JsonClass(generateAdapter = true)
    internal data class ApiName(

        val name: String,

        @Json(name = "name_sub")
        val subName: String?,

        @Json(name = "name_kana")
        val nameOfReading: String?
    )

    @JsonClass(generateAdapter = true)
    internal data class ApiContact(

        val address: String,

        @Json(name = "tel")
        val phoneNumber: String
    )

    @JsonClass(generateAdapter = true)
    internal data class ApiCategories(

        @Json(name = "category_name_l")
        val largeCategoryNames: Array<String>,

        @Json(name = "category_name_s")
        val smallCategoryNames: Array<String>
    )

    @JsonClass(generateAdapter = true)
    internal data class ApiLocation(

        @Json(name = "latitude_wgs84")
        val latitude: Double,

        @Json(name = "longitude_wgs84")
        val longitude: Double,

        val area: ApiArea
    )

    @JsonClass(generateAdapter = true)
    internal data class ApiArea(

        @Json(name = "areaname_s")
        val smallAreaName: String
    )

    @JsonClass(generateAdapter = true)
    internal data class ApiImage(

        @Json(name = "thumbnail")
        val thumbnailUrl: String?,

        @Json(name = "qrcode")
        val qrCodeUrl: String
    )
}