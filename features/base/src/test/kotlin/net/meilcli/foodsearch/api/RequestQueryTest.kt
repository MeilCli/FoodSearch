package net.meilcli.foodsearch.api

import org.junit.Test

class RequestQueryTest {

    class MockQuery(override val name: String, val value: Int) : IQuery {

        override fun hasValue(): Boolean {
            return true
        }

        override fun toValue(): String {
            return value.toString()
        }
    }

    @Test
    fun testCreateQuery() {
        val requestQuery = RequestQuery().apply {
            queries["q1"] = MockQuery("q1", 1)
            queries["q2"] = MockQuery("q2", 2)
        }

        assert(requestQuery.createQuery() == "q1=1&q2=2")
    }
}