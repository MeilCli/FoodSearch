package net.meilcli.foodsearch.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

// build variant source switch
@Suppress("PACKAGE_OR_CLASSIFIER_REDECLARATION")
object OkHttpClientFactory {

    fun create(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}