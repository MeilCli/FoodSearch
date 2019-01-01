package net.meilcli.foodsearch.api.gnavi.entities

data class Image(
    override val thumbnailUrl1: String?,
    override val thumbnailUrl2: String?,
    override val qrCodeUrl: String?
) : IImage