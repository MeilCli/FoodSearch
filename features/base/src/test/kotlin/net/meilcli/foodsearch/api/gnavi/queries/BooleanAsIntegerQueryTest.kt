package net.meilcli.foodsearch.api.gnavi.queries

import net.meilcli.foodsearch.api.RequestQuery
import org.junit.Test

class BooleanAsIntegerQueryTest {

    companion object {

        private const val queryName = "query"
    }

    class MockRequestQuery : RequestQuery() {

        val query = BooleanAsIntegerQuery(queryName)
        var property by query
    }

    @Test
    fun testHasValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.query.hasValue().not())

        mockRequestQuery.property = true

        assert(mockRequestQuery.query.hasValue())
    }

    @Test
    fun testToValue() {
        val mockRequestQuery = MockRequestQuery().apply {
            property = true
        }

        assert(mockRequestQuery.query.toValue() == "1")
    }

    @Test
    fun testGetValue() {
        val mockRequestQuery = MockRequestQuery().apply {
            property = true
        }

        assert(mockRequestQuery.property == true)
    }

    @Test
    fun testSetValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.property.not())
        assert(mockRequestQuery.queries.isEmpty())

        mockRequestQuery.property = true

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = true

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = false

        assert(mockRequestQuery.queries.isEmpty())
    }
}