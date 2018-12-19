package net.meilcli.foodsearch

import android.content.Intent
import android.net.Uri
import android.content.Intent.ACTION_VIEW as intentActionView
import android.content.Intent.CATEGORY_BROWSABLE as intentCategoryBrowsable
import net.meilcli.foodsearch.BuildConfig.APPLICATION_ID as applicationId

object Launcher {

    private fun createIntent(url: String) = Intent(intentActionView, Uri.parse(url)).apply {
        setPackage(applicationId)
        addCategory(intentCategoryBrowsable)
    }

    fun createSplashViewIntent() = createIntent(BuildConfig.linkUrlOfSplashView)

    fun createMainViewIntent() = createIntent(BuildConfig.linkUrlOfMainView)

    fun createGpsSearchViewIntent() = createIntent(BuildConfig.linkUrlOfGpsSearchView)
}