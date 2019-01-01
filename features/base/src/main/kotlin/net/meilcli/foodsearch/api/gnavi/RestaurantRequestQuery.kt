package net.meilcli.foodsearch.api.gnavi

import net.meilcli.foodsearch.BuildConfig
import net.meilcli.foodsearch.api.RequestQuery
import net.meilcli.foodsearch.api.gnavi.queries.BooleanAsIntegerQuery
import net.meilcli.foodsearch.api.gnavi.queries.RangeQuery
import net.meilcli.foodsearch.api.queries.DoubleQuery
import net.meilcli.foodsearch.api.queries.IntegerQuery
import net.meilcli.foodsearch.api.queries.StringArrayQuery
import net.meilcli.foodsearch.api.queries.StringQuery

class RestaurantRequestQuery : RequestQuery() {

    private var token by StringQuery("keyid")

    var ids by StringArrayQuery("id")
    var largeArea by StringQuery("areacode_l")
    var middleArea by StringQuery("areacode_m")
    var smallArea by StringQuery("areacode_s")
    var largeCategory by StringArrayQuery("category_l")
    var smallCategory by StringArrayQuery("category_s")
    var latitude by DoubleQuery("latitude")
    var longitude by DoubleQuery("longitude")
    var range by RangeQuery("range")
    var count by IntegerQuery("hit_per_page")
    var page by IntegerQuery("offset_page")
    var searchWord by StringArrayQuery("freeword")
    var canLunch by BooleanAsIntegerQuery("lunch")
    var canNoSmoking by BooleanAsIntegerQuery("no_smoking")
    var canCreditCard by BooleanAsIntegerQuery("card")
    var canDrinkAll by BooleanAsIntegerQuery("bottomless_cup")
    var hasPrivateRoom by BooleanAsIntegerQuery("private_room")
    var isMidnightOpen by BooleanAsIntegerQuery("midnight")
    var hasParking by BooleanAsIntegerQuery("parking")
    var hasPowerSupply by BooleanAsIntegerQuery("outret")
    var hasWifi by BooleanAsIntegerQuery("wifi")
    var canEatAll by BooleanAsIntegerQuery("buffet")
    var canSeeSports by BooleanAsIntegerQuery("sports")
    var isUntilMorningOpen by BooleanAsIntegerQuery("until_morning")
    var hasLunchDesert by BooleanAsIntegerQuery("lunch_desert")
    var canPet by BooleanAsIntegerQuery("with_pet")
    var canDelivery by BooleanAsIntegerQuery("deliverly")
    var canElectronicMoney by BooleanAsIntegerQuery("e_money")
    var hasDarts by BooleanAsIntegerQuery("darts")
    var canReserveInWeb by BooleanAsIntegerQuery("web_reserve")

    init {
        token = BuildConfig.gnaviApiKey
    }
}