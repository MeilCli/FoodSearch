package net.meilcli.foodsearch.api

open class RequestQuery : IRequestQuery {

    override val queries = hashMapOf<String, IQuery>()

    override fun createQuery(): String {
        return queries.values
            .filter { it.hasValue() }
            .joinToString(separator = "&") { "${it.name}=${it.toValue()}" }
    }
}