package net.meilcli.foodsearch.api.gnavi.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Category(override val name: String, override val code: String?) : ICategory

@JsonClass(generateAdapter = true)
data class LargeCategory(

    @Json(name = "category_l_name")
    override val name: String,

    @Json(name = "category_l_code")
    override val code: String
) : ICategory

@JsonClass(generateAdapter = true)
data class SmallCategory(

    @Json(name = "category_s_name")
    override val name: String,

    @Json(name = "category_s_code")
    override val code: String,

    @Json(name = "category_l_code")
    val largeCategoryCode: String
) : ICategory