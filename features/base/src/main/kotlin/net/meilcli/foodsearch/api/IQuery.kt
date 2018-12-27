package net.meilcli.foodsearch.api

interface IQuery {

    val name: String

    fun hasValue(): Boolean

    fun toValue(): String
}