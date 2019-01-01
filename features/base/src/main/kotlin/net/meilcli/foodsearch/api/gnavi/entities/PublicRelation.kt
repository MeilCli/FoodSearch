package net.meilcli.foodsearch.api.gnavi.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PublicRelation(
    @Json(name = "pr_short")
    override val shortText: String,

    @Json(name = "pr_long")
    override val longText: String
) : IPublicRelation