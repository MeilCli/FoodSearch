package net.meilcli.foodsearch.api.gnavi.entities

interface ISearchResponse<out T> {

    val totalCount: Int
    val items: List<T>
}