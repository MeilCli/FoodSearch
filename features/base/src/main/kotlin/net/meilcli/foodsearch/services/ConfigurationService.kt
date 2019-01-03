package net.meilcli.foodsearch.services

import android.content.Context
import net.meilcli.foodsearch.Language
import net.meilcli.foodsearch.R

class ConfigurationService(private val context: Context) : IConfigurationService {

    override val currentLanguage = when (context.getString(R.string.language)) {
        Language.Japanese.value -> Language.Japanese
        Language.English.value -> Language.English
        else -> Language.English
    }
}