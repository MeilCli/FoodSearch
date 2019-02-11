package net.meilcli.foodsearch.api

import okhttp3.Request

open class ApiClient : IApiClient {

    companion object {

        private val httpClient = OkHttpClientFactory.create()
    }

    override suspend fun get(url: String, query: IRequestQuery): String {
        val request = Request.Builder()
            .url("$url?${query.createQuery()}")
            .get()
            .build()
        val response = httpClient.newCall(request).execute()
        val body = requireNotNull(response.body()?.string())

        if (response.isSuccessful) {
            return body
        }

        throw ApiException(body)
    }
}