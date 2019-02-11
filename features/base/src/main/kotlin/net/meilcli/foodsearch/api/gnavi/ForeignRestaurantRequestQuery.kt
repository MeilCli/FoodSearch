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

class ForeignRestaurantRequestQuery : RequestQuery(), IRestaurantRequestQuery {

    private var token by StringQuery("keyid")

    override var ids by StringArrayQuery("id")
    var language by LanguageQuery("lang")
    override var largeArea by StringQuery("areacode_l")
    override var middleArea by StringQuery("areacode_m")
    override var smallArea by StringQuery("areacode_s")
    override var largeCategories by StringArrayQuery("category_l")
    override var smallCategories by StringArrayQuery("category_s")
    override var latitude by DoubleQuery("latitude")
    override var longitude by DoubleQuery("longitude")
    override var range by RangeQuery("range")
    override var count by IntegerQuery("hit_per_page")
    override var page by IntegerQuery("offset_page")
    override var searchWords by StringArrayQuery("freeword")
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