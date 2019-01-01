package net.meilcli.foodsearch.api.gnavi.entities

import java.util.*

data class Restaurant(
    override val id: String,
    override val updateAt: Date,
    override val name: String,
    override val nameOfReading: String,
    override val location: ILocation,
    override val smallArea: IArea,
    override val largeCategories: Array<ICategory>,
    override val smallCategories: Array<ICategory>,
    override val url: String?,
    override val urlForMobile: String?,
    override val couponUrl: String?,
    override val couponUrlForMobile: String?,
    override val image: IImage,
    override val openTime: String,
    override val holiday: String,
    override val access: String,
    override val contact: IContact,
    override val publicRelation: IPublicRelation,
    override val averageBudget: Int,
    override val creditCard: String?,
    override val electronicMoney: String?
) : IRestaurant