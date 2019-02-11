package net.meilcli.foodsearch

import net.meilcli.foodsearch.services.IConfigurationService
import net.meilcli.foodsearch.services.IGnaviService
import net.meilcli.foodsearch.services.IJsonService

interface IFoodSearchApplication {

    val configurationService: IConfigurationService
    val gnaviService: IGnaviService
    val jsonService: IJsonService
}