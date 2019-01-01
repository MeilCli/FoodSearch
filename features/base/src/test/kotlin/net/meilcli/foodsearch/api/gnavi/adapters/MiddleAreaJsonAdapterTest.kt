package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.Moshi
import net.meilcli.foodsearch.api.gnavi.GnaviJsonAdapterFactory
import net.meilcli.foodsearch.api.gnavi.entities.MiddleArea
import org.junit.Test
import kotlin.text.Charsets.UTF_8 as Utf8Charset

class MiddleAreaJsonAdapterTest {

    private val moshi = Moshi.Builder().add(GnaviJsonAdapterFactory).build()

    @Test
    fun testFromJson() {
        requireNotNull(javaClass.classLoader?.getResourceAsStream("gnavi/MiddleArea.json")).use {
            val json = it.readBytes().toString(Utf8Charset)
            val area = moshi.adapter(MiddleArea::class.java).fromJson(json)

            checkNotNull(area)
            assert(area.code == "code")
            assert(area.name == "name")
            assert(area.prefectureArea.code == "pref_code")
            assert(area.prefectureArea.name == "pref_name")
            assert(area.largeArea.code == "largeArea_code")
            assert(area.largeArea.name == "largeArea_name")
        }
    }
}