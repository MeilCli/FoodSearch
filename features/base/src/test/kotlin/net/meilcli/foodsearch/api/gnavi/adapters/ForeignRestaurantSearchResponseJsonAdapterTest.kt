package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.Moshi
import net.meilcli.foodsearch.api.gnavi.GnaviJsonAdapterFactory
import net.meilcli.foodsearch.api.gnavi.entities.ForeignRestaurantSearchResponse
import org.junit.Test
import kotlin.text.Charsets.UTF_8 as Utf8Charset

class ForeignRestaurantSearchResponseJsonAdapterTest {

    private val moshi = Moshi.Builder().add(GnaviJsonAdapterFactory).build()

    @Test
    fun testFromJson() {
        requireNotNull(javaClass.classLoader?.getResourceAsStream("gnavi/ForeignRestaurantSearchResponse.json")).use {
            val json = it.readBytes().toString(Utf8Charset)
            val restaurantSearchResponse = moshi.adapter(ForeignRestaurantSearchResponse::class.java).fromJson(json)

            checkNotNull(restaurantSearchResponse)

            assert(restaurantSearchResponse.totalCount == 100)
            assert(restaurantSearchResponse.items.isEmpty())
        }
    }
}