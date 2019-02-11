package net.meilcli.foodsearch.services

import net.meilcli.foodsearch.IList
import net.meilcli.foodsearch.api.gnavi.entities.LargeCategory

interface IJsonService {

    fun toLargeCategoriesJson(value: IList<LargeCategory>): String

    fun toLargeCategoriesValue(json: String): IList<LargeCategory>?
}