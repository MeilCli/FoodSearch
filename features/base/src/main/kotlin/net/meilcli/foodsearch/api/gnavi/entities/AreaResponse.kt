package net.meilcli.foodsearch.api.gnavi.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrefectureAreaResponse(@Json(name = "pref") val prefectureArea: List<PrefectureArea>)

@JsonClass(generateAdapter = true)
data class LargeAreaResponse(@Json(name = "garea_large") val largeArea: List<LargeArea>)

@JsonClass(generateAdapter = true)
data class MiddleAreaResponse(@Json(name = "garea_middle") val middleArea: List<MiddleArea>)

@JsonClass(generateAdapter = true)
data class SmallAreaResponse(@Json(name = "garea_small") val smallArea: List<SmallArea>)