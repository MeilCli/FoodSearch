package net.meilcli.foodsearch.api

import okhttp3.OkHttpClient

// build variant source switch
@Suppress("PACKAGE_OR_CLASSIFIER_REDECLARATION")
object OkHttpClientFactory {

    fun create(): OkHttpClient = OkHttpClient.Builder().build()
}