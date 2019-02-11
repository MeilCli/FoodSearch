package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.*
import net.meilcli.foodsearch.IJsonAdapterFactory
import net.meilcli.foodsearch.api.createNonNullJsonDateException
import net.meilcli.foodsearch.api.createNullPointerExceptionWhenConvertJson
import net.meilcli.foodsearch.api.gnavi.entities.IArea
import net.meilcli.foodsearch.api.gnavi.entities.SmallArea
import java.lang.reflect.Type
import net.meilcli.foodsearch.api.gnavi.adapters.SmallAreaJsonAdapter_ApiLargeAreaJsonAdapter as ApiLargeAreaJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.SmallAreaJsonAdapter_ApiMiddleAreaJsonAdapter as ApiMiddleAreaJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.SmallAreaJsonAdapter_ApiPrefectureAreaJsonAdapter as ApiPrefectureAreaJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.SmallAreaJsonAdapter_ApiSmallAreaJsonAdapter as ApiSmallAreaJsonAdapter

class SmallAreaJsonAdapter(moshi: Moshi) : JsonAdapter<SmallArea>() {

    // https://api.gnavi.co.jp/api/manual/areasmaster/

    private val apiSmallAreaJsonAdapter = moshi.adapter(ApiSmallArea::class.java)

    override fun fromJson(reader: JsonReader): SmallArea {
        val apiSmallArea = apiSmallAreaJsonAdapter.fromJson(reader) ?: throw createNonNullJsonDateException(reader)

        return SmallArea(
            name = apiSmallArea.name,
            code = apiSmallArea.code,
            middleArea = apiSmallArea.middleArea,
            largeArea = apiSmallArea.largeArea,
            prefectureArea = apiSmallArea.prefectureArea
        )
    }

    override fun toJson(writer: JsonWriter, value: SmallArea?) {
        value ?: throw createNullPointerExceptionWhenConvertJson()

        val apiSmallArea = ApiSmallArea(
            name = value.name,
            code = value.code,
            middleArea = ApiMiddleArea(
                name = value.middleArea.name,
                code = checkNotNull(value.middleArea.code)
            ),
            largeArea = ApiLargeArea(
                name = value.largeArea.name,
                code = checkNotNull(value.largeArea.code)
            ),
            prefectureArea = ApiPrefectureArea(
                name = value.prefectureArea.name,
                code = checkNotNull(value.prefectureArea.code)
            )
        )

        apiSmallAreaJsonAdapter.toJson(writer, apiSmallArea)
    }

    internal object InternalJsonAdapterFactory : IJsonAdapterFactory {

        override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
            return when (type) {
                ApiSmallArea::class.java -> ApiSmallAreaJsonAdapter(moshi)
                ApiMiddleArea::class.java -> ApiMiddleAreaJsonAdapter(moshi)
                ApiLargeArea::class.java -> ApiLargeAreaJsonAdapter(moshi)
                ApiPrefectureArea::class.java -> ApiPrefectureAreaJsonAdapter(moshi)
                else -> null
            }
        }
    }

    @JsonClass(generateAdapter = true)
    internal data class ApiSmallArea(

        @Json(name = "areaname_s")
        override val name: String,

        @Json(name = "areacode_s")
        override val code: String,

        @Json(name = "pref")
        val prefectureArea: ApiPrefectureArea,

        @Json(name = "garea_large")
        val largeArea: ApiLargeArea,

        @Json(name = "garea_middle")
        val middleArea: ApiMiddleArea
    ) : IArea

    @JsonClass(generateAdapter = true)
    internal data class ApiMiddleArea(

        @Json(name = "areaname_m")
        override val name: String,

        @Json(name = "areacode_m")
        override val code: String
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