package net.meilcli.foodsearch.api.queries

import net.meilcli.foodsearch.api.RequestQuery
import org.junit.Test

class DoubleQueryTest {

    companion object {

        private const val queryName = "query"
    }

    class MockRequestQuery : RequestQuery() {

        val query = DoubleQuery(queryName)
        var property by query
    }

    @Test
    fun testHasValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.query.hasValue().not())

        mockRequestQuery.property = 1.0

        assert(mockRequestQuery.query.hasValue())
    }

    @Test
    fun testToValue() {
        val mockRequestQuery = MockRequestQuery().apply {
            property = 1.0
        }

        assert(mockRequestQuery.query.toValue() == "1.0")
    }

    @Test
    fun testGetValue() {
        val mockRequestQuery = MockRequestQuery().apply {
            property = 1.0
        }

        assert(mockRequestQuery.property == 1.0)
    }

    @Test
    fun testSetValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.property == null)
        assert(mockRequestQuery.queries.isEmpty())

        mockRequestQuery.property = 1.0

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = 2.0

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = null

        assert(mockRequestQuery.queries.isEmpty())
    }
}