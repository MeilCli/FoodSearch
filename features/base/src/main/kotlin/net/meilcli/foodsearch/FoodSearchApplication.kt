package net.meilcli.foodsearch

import android.app.Application
import net.meilcli.foodsearch.services.ConfigurationService
import net.meilcli.foodsearch.services.GnaviService
import net.meilcli.foodsearch.services.IConfigurationService
import net.meilcli.foodsearch.services.IGnaviService

class FoodSearchApplication : Application(), IFoodSearchApplication {

    override lateinit var configurationService: IConfigurationService
    override lateinit var gnaviService: IGnaviService

    override fun onCreate() {
        super.onCreate()
        configurationService = ConfigurationService(this)
        gnaviService = GnaviService(configurationService)
    }
}