package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.Moshi
import net.meilcli.foodsearch.api.gnavi.GnaviJsonAdapterFactory
import net.meilcli.foodsearch.api.gnavi.entities.Area
import net.meilcli.foodsearch.api.gnavi.entities.SmallArea
import org.junit.Test
import kotlin.text.Charsets.UTF_8 as Utf8Charset

class SmallAreaJsonAdapterTest {

    private val moshi = Moshi.Builder().add(GnaviJsonAdapterFactory).build()

    @Test
    fun testFromJson() {
        requireNotNull(javaClass.classLoader?.getResourceAsStream("gnavi/SmallArea.json")).use {
            val json = it.readBytes().toString(Utf8Charset)
            val area = moshi.adapter(SmallArea::class.java).fromJson(json)

            checkNotNull(area)
            assert(area.code == "code")
            assert(area.name == "name")
            assert(area.prefectureArea.code == "pref_code")
            assert(area.prefectureArea.name == "pref_name")
            assert(area.largeArea.code == "largeArea_code")
            assert(area.largeArea.name == "largeArea_name")
            assert(area.middleArea.code == "middleArea_code")
            assert(area.middleArea.name == "middleArea_name")
        }
    }

    @Test
    fun testToJson() {
        val area = SmallArea(
            name = "name",
            code = "code",
            prefectureArea = Area(
                name = "prefectureName",
                code = "prefectureCode"
            ),
            largeArea = Area(
                name = "largeName",
                code = "largeCode"
            ),
            middleArea = Area(
                name = "middleName",
                code = "middleCode"
            )
        )

        val adapter = moshi.adapter(SmallArea::class.java)
        val json = adapter.toJson(area)
        val output = adapter.fromJson(json)

        checkNotNull(output)

        assert(output.name == "name")
        assert(output.code == "code")
        assert(output.prefectureArea.name == "prefectureName")
        assert(output.prefectureArea.code == "prefectureCode")
        assert(output.largeArea.name == "largeName")
        assert(output.largeArea.code == "largeCode")
        assert(output.middleArea.name == "middleName")
        assert(output.middleArea.code == "middleCode")
    }
}