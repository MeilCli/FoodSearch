package net.meilcli.foodsearch.api.gnavi.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestaurantSearchResponse(

    @Json(name = "total_hit_count")
    override val totalCount: Int,

    @Json(name = "rest")
    override val items: List<Restaurant>
) : ISearchResponse<Restaurant>