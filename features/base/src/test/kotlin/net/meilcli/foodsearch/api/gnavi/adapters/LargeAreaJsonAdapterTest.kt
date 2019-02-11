package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.Moshi
import net.meilcli.foodsearch.api.gnavi.GnaviJsonAdapterFactory
import net.meilcli.foodsearch.api.gnavi.entities.Area
import net.meilcli.foodsearch.api.gnavi.entities.LargeArea
import org.junit.Test
import kotlin.text.Charsets.UTF_8 as Utf8Charset

class LargeAreaJsonAdapterTest {

    private val moshi = Moshi.Builder().add(GnaviJsonAdapterFactory).build()

    @Test
    fun testFromJson() {
        requireNotNull(javaClass.classLoader?.getResourceAsStream("gnavi/LargeArea.json")).use {
            val json = it.readBytes().toString(Utf8Charset)
            val area = moshi.adapter(LargeArea::class.java).fromJson(json)

            checkNotNull(area)
            assert(area.code == "code")
            assert(area.name == "name")
            assert(area.prefectureArea.code == "pref_code")
            assert(area.prefectureArea.name == "pref_name")
        }
    }

    @Test
    fun testToJson() {
        val area = LargeArea(
            name = "name",
            code = "code",
            prefectureArea = Area(
                name = "prefectureName",
                code = "prefectureCode"
            )
        )

        val adapter = moshi.adapter(LargeArea::class.java)
        val json = adapter.toJson(area)
        val output = adapter.fromJson(json)

        checkNotNull(output)

        assert(output.name == "name")
        assert(output.code == "code")
        assert(output.prefectureArea.name == "prefectureName")
        assert(output.prefectureArea.code == "prefectureCode")
    }
}