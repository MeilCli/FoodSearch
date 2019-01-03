package net.meilcli.foodsearch.services

import net.meilcli.foodsearch.Language

interface IConfigurationService {

    val currentLanguage: Language
}