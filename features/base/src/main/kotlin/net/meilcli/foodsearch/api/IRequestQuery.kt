package net.meilcli.foodsearch.api

interface IRequestQuery {

    val queries: HashMap<String, IQuery>

    fun createQuery(): String
}