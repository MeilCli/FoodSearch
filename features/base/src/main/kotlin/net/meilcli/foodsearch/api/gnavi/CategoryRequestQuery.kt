package net.meilcli.foodsearch.api.gnavi

import net.meilcli.foodsearch.BuildConfig
import net.meilcli.foodsearch.api.RequestQuery
import net.meilcli.foodsearch.api.gnavi.queries.LanguageQuery
import net.meilcli.foodsearch.api.queries.StringQuery

class CategoryRequestQuery : RequestQuery() {

    private var token by StringQuery("keyid")

    var language by LanguageQuery("lang")

    init {
        token = BuildConfig.gnaviApiKey
    }
}