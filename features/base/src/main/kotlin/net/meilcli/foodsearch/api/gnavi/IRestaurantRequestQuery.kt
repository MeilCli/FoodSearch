package net.meilcli.foodsearch.api.gnavi

import net.meilcli.foodsearch.api.IRequestQuery

interface IRestaurantRequestQuery : IRequestQuery {

    var ids: Array<String>?
    var largeArea: String?
    var middleArea: String?
    var smallArea: String?
    var largeCategories: Array<String>?
    var smallCategories: Array<String>?
    var latitude: Double?
    var longitude: Double?
    var range: Range
    var count: Int?
    var page: Int?
    var searchWords: Array<String>?
}