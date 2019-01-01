package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.Moshi
import net.meilcli.foodsearch.api.gnavi.GnaviJsonAdapterFactory
import net.meilcli.foodsearch.api.gnavi.entities.Error
import org.junit.Test
import kotlin.text.Charsets.UTF_8 as Utf8Charset

class ErrorJsonAdapterTest {

    private val moshi = Moshi.Builder().add(GnaviJsonAdapterFactory).build()

    @Test
    fun testFromJson() {
        requireNotNull(javaClass.classLoader?.getResourceAsStream("gnavi/Error.json")).use {
            val json = it.readBytes().toString(Utf8Charset)
            val error = moshi.adapter(Error::class.java).fromJson(json)

            checkNotNull(error)
            assert(error.code == 400)
            assert(error.message == "message")
        }
    }

    @Test
    fun testToJson() {
        val error = Error("message", 404)
        val errorJsonAdapter = moshi.adapter(Error::class.java)
        val json = errorJsonAdapter.toJson(error)
        val output = errorJsonAdapter.fromJson(json)

        checkNotNull(output)
        assert(output.code == 404)
        assert(output.message == "message")
    }
}