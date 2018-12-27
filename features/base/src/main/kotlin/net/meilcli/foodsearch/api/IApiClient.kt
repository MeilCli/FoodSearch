package net.meilcli.foodsearch.api

interface IApiClient {

    suspend fun get(url: String, query: IRequestQuery): String
}