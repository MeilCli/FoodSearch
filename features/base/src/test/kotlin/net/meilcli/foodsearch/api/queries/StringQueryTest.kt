package net.meilcli.foodsearch.api.queries

import net.meilcli.foodsearch.api.RequestQuery
import net.meilcli.foodsearch.extensions.toUrlEncodedValue
import org.junit.Test

class StringQueryTest {

    companion object {

        private const val queryName = "query"
    }

    class MockRequestQuery : RequestQuery() {

        val query = StringQuery(queryName)
        var property by query
    }

    @Test
    fun testHasValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.query.hasValue().not())

        mockRequestQuery.property = ""

        assert(mockRequestQuery.query.hasValue())
    }

    @Test
    fun testToValue() {
        val mockRequestQuery = MockRequestQuery().apply {
            property = "テスト"
        }

        assert(mockRequestQuery.query.toValue() == "テスト".toUrlEncodedValue())
    }

    @Test
    fun testGetValue() {
        val mockRequestQuery = MockRequestQuery().apply {
            property = "aaa"
        }

        assert(mockRequestQuery.property == "aaa")
    }

    @Test
    fun testSetValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.property == null)
        assert(mockRequestQuery.queries.isEmpty())

        mockRequestQuery.property = "1"

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = "2"

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = null

        assert(mockRequestQuery.queries.isEmpty())
    }
}