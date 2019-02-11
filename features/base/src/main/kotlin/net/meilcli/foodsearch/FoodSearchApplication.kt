package net.meilcli.foodsearch

import android.app.Application
import net.meilcli.foodsearch.services.*

class FoodSearchApplication : Application(), IFoodSearchApplication {

    override lateinit var configurationService: IConfigurationService
    override lateinit var gnaviService: IGnaviService
    override lateinit var jsonService: IJsonService

    override fun onCreate() {
        super.onCreate()
        configurationService = ConfigurationService(this)
        gnaviService = GnaviService(configurationService)
        jsonService = JsonService()
    }
}