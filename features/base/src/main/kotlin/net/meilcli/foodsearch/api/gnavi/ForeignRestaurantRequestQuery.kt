package net.meilcli.foodsearch.api.gnavi

import net.meilcli.foodsearch.BuildConfig
import net.meilcli.foodsearch.api.RequestQuery
import net.meilcli.foodsearch.api.gnavi.queries.BooleanAsIntegerQuery
import net.meilcli.foodsearch.api.gnavi.queries.LanguageQuery
import net.meilcli.foodsearch.api.gnavi.queries.RangeQuery
import net.meilcli.foodsearch.api.queries.DoubleQuery
import net.meilcli.foodsearch.api.queries.IntegerQuery
import net.meilcli.foodsearch.api.queries.StringArrayQuery
import net.meilcli.foodsearch.api.queries.StringQuery

class ForeignRestaurantRequestQuery : RequestQuery() {

    private var token by StringQuery("keyid")

    var ids by StringArrayQuery("id")
    var language by LanguageQuery("lang")
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
    var hasEnglishSpeakingStaff by BooleanAsIntegerQuery("english_speaking_staff")
    var hasEnglishMenu by BooleanAsIntegerQuery("english_menu")
    var hasVegetarianMenu by BooleanAsIntegerQuery("vegetarian_menu_options")
    var hasReligiousMenu by BooleanAsIntegerQuery("religious_menu_options")
    var hasWifi by BooleanAsIntegerQuery("wifi")
    var canCreditCard by BooleanAsIntegerQuery("card")
    var hasPrivateRoom by BooleanAsIntegerQuery("private_room")
    var canNoSmoking by BooleanAsIntegerQuery("no_smoking")

    init {
        token = BuildConfig.gnaviApiKey
    }
}