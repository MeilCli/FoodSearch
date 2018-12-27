package net.meilcli.foodsearch.api

import okhttp3.OkHttpClient
import okhttp3.Request

class ApiClient : IApiClient {

    companion object {

        private val httpClient = OkHttpClient.Builder().build()
    }

    override suspend fun get(url: String, query: IRequestQuery): String {
        val request = Request.Builder()
            .url("$url?${query.createQuery()}")
            .get()
            .build()
        val response = httpClient.newCall(request).execute()

        if (response.isSuccessful) {
            return response.message()
        }

        throw ApiException(response.message())
    }
}