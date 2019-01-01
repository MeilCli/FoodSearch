package net.meilcli.foodsearch.api.queries

import net.meilcli.foodsearch.api.RequestQuery
import net.meilcli.foodsearch.extensions.toUrlEncodedValue
import org.junit.Test

class StringArrayQueryTest {

    companion object {

        private const val queryName = "query"
    }

    class MockRequestQuery : RequestQuery() {

        val query = StringArrayQuery(queryName)
        var property by query
    }

    @Test
    fun testHasValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.query.hasValue().not())

        mockRequestQuery.property = emptyArray()

        assert(mockRequestQuery.query.hasValue())
    }

    @Test
    fun testToValue() {
        val mockRequestQuery = MockRequestQuery().apply {
            property = emptyArray()
        }

        assert(mockRequestQuery.query.toValue() == "")

        mockRequestQuery.property = arrayOf("テスト")
        assert(mockRequestQuery.query.toValue() == "テスト".toUrlEncodedValue())

        mockRequestQuery.property = arrayOf("テスト1", "テスト2")
        assert(mockRequestQuery.query.toValue() == "テスト1".toUrlEncodedValue() + "," + "テスト2".toUrlEncodedValue())
    }

    @Test
    fun testGetValue() {
        val array = arrayOf("element")
        val mockRequestQuery = MockRequestQuery().apply {
            property = array
        }

        assert(mockRequestQuery.property!![0] == "element")
    }

    @Test
    fun testSetValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.property == null)
        assert(mockRequestQuery.queries.isEmpty())

        mockRequestQuery.property = emptyArray()

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = emptyArray()

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = null

        assert(mockRequestQuery.queries.isEmpty())
    }
}