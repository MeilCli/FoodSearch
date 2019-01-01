package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.Moshi
import net.meilcli.foodsearch.api.gnavi.GnaviJsonAdapterFactory
import net.meilcli.foodsearch.api.gnavi.entities.ForeignRestaurant
import org.junit.Test
import java.text.SimpleDateFormat
import kotlin.text.Charsets.UTF_8 as Utf8Charset

class ForeignRestaurantJsonAdapterTest {

    private val moshi = Moshi.Builder().add(GnaviJsonAdapterFactory).build()
    private val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

    @Test
    fun testFromJson() {
        requireNotNull(javaClass.classLoader?.getResourceAsStream("gnavi/ForeignRestaurant.json")).use {
            val json = it.readBytes().toString(Utf8Charset)
            val restaurant = moshi.adapter(ForeignRestaurant::class.java).fromJson(json)

            checkNotNull(restaurant)
            assert(restaurant.id == "id")
            assert(dateFormat.format(restaurant.updateAt) == "2018/01/01 10:11:12")
            assert(restaurant.name == "name")
            assert(restaurant.nameOfReading == "nameOfReading")
            assert(restaurant.location.latitude == 114.514)
            assert(restaurant.location.longitude == 114.514)
            assert(restaurant.url == "https://google.com")
            assert(restaurant.urlForMobile == "https://google.com")
            assert(restaurant.couponUrl == null)
            assert(restaurant.couponUrlForMobile == null)
            assert(restaurant.image.thumbnailUrl1 == null)
            assert(restaurant.image.thumbnailUrl2 == null)
            assert(restaurant.image.qrCodeUrl == null)
            assert(restaurant.contact.address == "Japan")
            assert(restaurant.contact.phoneNumber == "000-000-000")
            assert(restaurant.openTime == "opentime")
            assert(restaurant.holiday == "holiday")
            assert(restaurant.access == "line駅東口 徒歩3分")
            assert(restaurant.publicRelation.shortText == "short")
            assert(restaurant.publicRelation.longText == "long")
            assert(restaurant.smallArea.name == "area")
            assert(restaurant.smallArea.code == null)
            assert(restaurant.smallCategories.size == 1)
            assert(restaurant.smallCategories[0].name == "name")
            assert(restaurant.smallCategories[0].code == null)
            assert(restaurant.largeCategories.size == 1)
            assert(restaurant.largeCategories[0].name == "name")
            assert(restaurant.largeCategories[0].code == null)
            assert(restaurant.averageBudget == 1000)
            assert(restaurant.creditCard == "credit")
            assert(restaurant.electronicMoney == null)
        }
    }
}