package net.meilcli.foodsearch

import android.content.Intent
import android.net.Uri
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import android.content.Intent.ACTION_VIEW as intentActionView
import android.content.Intent.CATEGORY_BROWSABLE as intentCategoryBrowsable
import net.meilcli.foodsearch.BuildConfig.APPLICATION_ID as applicationId

@RunWith(RobolectricTestRunner::class)
class LauncherTest {

    private fun checkIntentAttributes(intent: Intent) {
        assert(intent.action == intentActionView)
        assert(intent.hasCategory(intentCategoryBrowsable))
        assert(intent.`package` == applicationId)
    }

    @Test
    fun testCreateSplashViewIntent() {
        val intent = Launcher.createSplashViewIntent()

        checkIntentAttributes(intent)
        assert(intent.data == Uri.parse(BuildConfig.linkUrlOfSplashView))
    }

    @Test
    fun testCreateMainViewIntent() {
        val intent = Launcher.createMainViewIntent()

        checkIntentAttributes(intent)
        assert(intent.data == Uri.parse(BuildConfig.linkUrlOfMainView))
    }

    @Test
    fun testCreateGpsSearchViewIntent() {
        val intent = Launcher.createGpsSearchViewIntent()

        checkIntentAttributes(intent)
        assert(intent.data == Uri.parse(BuildConfig.linkUrlOfGpsSearchView))
    }
}