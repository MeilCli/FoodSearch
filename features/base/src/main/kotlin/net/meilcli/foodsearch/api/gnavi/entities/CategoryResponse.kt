package net.meilcli.foodsearch.api.gnavi.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LargeCategoryResponse(@Json(name = "category_l") val largeCategories: List<LargeCategory>)

@JsonClass(generateAdapter = true)
data class SmallCategoryResponse(@Json(name = "category_s") val smallCategories: List<SmallCategory>)