package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.*
import net.meilcli.foodsearch.IJsonAdapterFactory
import net.meilcli.foodsearch.api.createNonNullJsonDateException
import net.meilcli.foodsearch.api.gnavi.entities.IArea
import net.meilcli.foodsearch.api.gnavi.entities.MiddleArea
import java.lang.reflect.Type
import net.meilcli.foodsearch.api.gnavi.adapters.MiddleAreaJsonAdapter_ApiLargeAreaJsonAdapter as ApiLargeAreaJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.MiddleAreaJsonAdapter_ApiMiddleAreaJsonAdapter as ApiMiddleAreaJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.MiddleAreaJsonAdapter_ApiPrefectureAreaJsonAdapter as ApiPrefectureAreaJsonAdapter

class MiddleAreaJsonAdapter(moshi: Moshi) : JsonAdapter<MiddleArea>() {

    // https://api.gnavi.co.jp/api/manual/areammaster/

    private val apiMiddleAreaJsonAdapter = moshi.adapter(ApiMiddleArea::class.java)

    override fun fromJson(reader: JsonReader): MiddleArea {
        val apiMiddleArea = apiMiddleAreaJsonAdapter.fromJson(reader) ?: throw createNonNullJsonDateException(reader)

        return MiddleArea(
            name = apiMiddleArea.name,
            code = apiMiddleArea.code,
            largeArea = apiMiddleArea.largeArea,
            prefectureArea = apiMiddleArea.prefectureArea
        )
    }

    override fun toJson(writer: JsonWriter, value: MiddleArea?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    internal object InternalJsonAdapterFactory : IJsonAdapterFactory {

        override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
            return when (type) {
                ApiMiddleArea::class.java -> ApiMiddleAreaJsonAdapter(moshi)
                ApiLargeArea::class.java -> ApiLargeAreaJsonAdapter(moshi)
                ApiPrefectureArea::class.java -> ApiPrefectureAreaJsonAdapter(moshi)
                else -> null
            }
        }
    }

    @JsonClass(generateAdapter = true)
    internal data class ApiMiddleArea(

        @Json(name = "areaname_m")
        override val name: String,

        @Json(name = "areacode_m")
        override val code: String,

        @Json(name = "pref")
        val prefectureArea: ApiPrefectureArea,

        @Json(name = "garea_large")
        val largeArea: ApiLargeArea
    ) : IArea

    @JsonClass(generateAdapter = true)
    internal data class ApiLargeArea(

        @Json(name = "areaname_l")
        override val name: String,

        @Json(name = "areacode_l")
        override val code: String
    ) : IArea

    @JsonClass(generateAdapter = true)
    internal data class ApiPrefectureArea(

        @Json(name = "pref_name")
        override val name: String,

        @Json(name = "pref_code")
        override val code: String
    ) : IArea
}