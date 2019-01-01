package net.meilcli.foodsearch.api.gnavi.entities

import java.util.*

interface IRestaurant {

    val id: String
    val updateAt: Date
    val name: String
    val nameOfReading: String
    val location: ILocation
    val smallArea: IArea
    val largeCategories: Array<ICategory>
    val smallCategories: Array<ICategory>
    val url: String?
    val urlForMobile: String?
    val couponUrl: String?
    val couponUrlForMobile: String?
    val image: IImage
    val openTime: String
    val holiday: String
    val access: String
    val contact: IContact
    val publicRelation: IPublicRelation
    val averageBudget: Int
    val creditCard: String?
    val electronicMoney: String?
}