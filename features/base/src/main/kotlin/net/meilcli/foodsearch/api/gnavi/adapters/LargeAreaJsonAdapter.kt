package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.*
import net.meilcli.foodsearch.IJsonAdapterFactory
import net.meilcli.foodsearch.api.createNonNullJsonDateException
import net.meilcli.foodsearch.api.createNullPointerExceptionWhenConvertJson
import net.meilcli.foodsearch.api.gnavi.entities.IArea
import net.meilcli.foodsearch.api.gnavi.entities.LargeArea
import java.lang.reflect.Type
import net.meilcli.foodsearch.api.gnavi.adapters.LargeAreaJsonAdapter_ApiLargeAreaJsonAdapter as ApiLargeAreaJsonAdapter
import net.meilcli.foodsearch.api.gnavi.adapters.LargeAreaJsonAdapter_ApiPrefectureAreaJsonAdapter as ApiPrefectureAreaJsonAdapter

class LargeAreaJsonAdapter(moshi: Moshi) : JsonAdapter<LargeArea>() {

    // https://api.gnavi.co.jp/api/manual/arealmaster/

    private val apiLargeAreaJsonAdapter = moshi.adapter(ApiLargeArea::class.java)

    override fun fromJson(reader: JsonReader): LargeArea {
        val apiLargeArea = apiLargeAreaJsonAdapter.fromJson(reader) ?: throw createNonNullJsonDateException(reader)

        return LargeArea(
            name = apiLargeArea.name,
            code = apiLargeArea.code,
            prefectureArea = apiLargeArea.prefectureArea
        )
    }

    override fun toJson(writer: JsonWriter, value: LargeArea?) {
        value ?: throw createNullPointerExceptionWhenConvertJson()

        val apiLargeArea = ApiLargeArea(
            name = value.name,
            code = value.code,
            prefectureArea = ApiPrefectureArea(
                name = value.prefectureArea.name,
                code = checkNotNull(value.prefectureArea.code)
            )
        )

        apiLargeAreaJsonAdapter.toJson(writer, apiLargeArea)
    }

    internal object InternalJsonAdapterFactory : IJsonAdapterFactory {

        override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
            return when (type) {
                ApiLargeArea::class.java -> ApiLargeAreaJsonAdapter(moshi)
                ApiPrefectureArea::class.java -> ApiPrefectureAreaJsonAdapter(moshi)
                else -> null
            }
        }
    }

    @JsonClass(generateAdapter = true)
    internal data class ApiLargeArea(

        @Json(name = "areaname_l")
        override val name: String,

        @Json(name = "areacode_l")
        override val code: String,

        @Json(name = "pref")
        val prefectureArea: ApiPrefectureArea
    ) : IArea

    @JsonClass(generateAdapter = true)
    internal data class ApiPrefectureArea(

        @Json(name = "pref_name")
        override val name: String,

        @Json(name = "pref_code")
        override val code: String
    ) : IArea
}