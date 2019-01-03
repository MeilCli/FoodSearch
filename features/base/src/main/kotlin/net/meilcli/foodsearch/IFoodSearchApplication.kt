package net.meilcli.foodsearch

import net.meilcli.foodsearch.services.IConfigurationService
import net.meilcli.foodsearch.services.IGnaviService

interface IFoodSearchApplication {

    val configurationService: IConfigurationService
    val gnaviService: IGnaviService
}