package net.meilcli.foodsearch.api.gnavi.queries

import net.meilcli.foodsearch.api.RequestQuery
import net.meilcli.foodsearch.api.gnavi.Range
import org.junit.Test

class RangeQueryTest {

    companion object {

        private const val queryName = "query"
    }

    class MockRequestQuery : RequestQuery() {

        val query = RangeQuery(queryName)
        var property by query
    }

    @Test
    fun testHasValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.query.hasValue().not())

        mockRequestQuery.property = Range.M1000

        assert(mockRequestQuery.query.hasValue())
    }

    @Test
    fun testToValue() {
        val mockRequestQuery = MockRequestQuery().apply {
            property = Range.M1000
        }

        assert(mockRequestQuery.query.toValue() == Range.M1000.queryValue)
    }

    @Test
    fun testGetValue() {
        val mockRequestQuery = MockRequestQuery().apply {
            property = Range.M1000
        }

        assert(mockRequestQuery.property == Range.M1000)
    }

    @Test
    fun testSetValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.property == Range.M500)
        assert(mockRequestQuery.queries.isEmpty())

        mockRequestQuery.property = Range.M1000

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = Range.M2000

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = Range.M500

        assert(mockRequestQuery.queries.isEmpty())
    }
}