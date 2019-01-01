package net.meilcli.foodsearch.api.gnavi.queries

import net.meilcli.foodsearch.api.RequestQuery
import net.meilcli.foodsearch.api.gnavi.Language
import org.junit.Test

class LanguageQueryTest {

    companion object {

        private const val queryName = "query"
    }

    class MockRequestQuery : RequestQuery() {

        val query = LanguageQuery(queryName)
        var property by query
    }

    @Test
    fun testHasValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.query.hasValue().not())

        mockRequestQuery.property = Language.English

        assert(mockRequestQuery.query.hasValue())
    }

    @Test
    fun testToValue() {
        val mockRequestQuery = MockRequestQuery().apply {
            property = Language.English
        }

        assert(mockRequestQuery.query.toValue() == "en")
    }

    @Test
    fun testGetValue() {
        val mockRequestQuery = MockRequestQuery().apply {
            property = Language.English
        }

        assert(mockRequestQuery.property == Language.English)
    }

    @Test
    fun testSetValue() {
        val mockRequestQuery = MockRequestQuery()

        assert(mockRequestQuery.property == Language.Japanese)
        assert(mockRequestQuery.queries.isEmpty())

        mockRequestQuery.property = Language.English

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = Language.English

        assert(mockRequestQuery.queries.size == 1)
        assert(mockRequestQuery.queries.containsValue(mockRequestQuery.query))
        assert(mockRequestQuery.queries.containsKey(queryName))

        mockRequestQuery.property = Language.Japanese

        assert(mockRequestQuery.queries.isEmpty())
    }
}