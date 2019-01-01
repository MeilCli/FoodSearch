package net.meilcli.foodsearch.api.gnavi.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Area(override val name: String, override val code: String?) : IArea

@JsonClass(generateAdapter = true)
data class PrefectureArea(

    @Json(name = "pref_name")
    override val name: String,

    @Json(name = "pref_code")
    override val code: String
) : IArea

data class LargeArea(
    override val name: String,
    override val code: String,
    val prefectureArea: IArea
) : IArea

data class MiddleArea(
    override val name: String,
    override val code: String,
    val prefectureArea: IArea,
    val largeArea: IArea
) : IArea

data class SmallArea(
    override val name: String,
    override val code: String,
    val prefectureArea: IArea,
    val largeArea: IArea,
    val middleArea: IArea
) : IArea