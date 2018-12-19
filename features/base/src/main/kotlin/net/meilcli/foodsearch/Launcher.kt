package net.meilcli.foodsearch

import android.content.Intent
import android.net.Uri
import android.content.Intent.ACTION_VIEW as intentActionView
import android.content.Intent.CATEGORY_BROWSABLE as intentCategoryBrowsable
import net.meilcli.foodsearch.BuildConfig.APPLICATION_ID as applicationId

object Launcher {

    fun createSplashViewIntent() = Intent(intentActionView, Uri.parse(BuildConfig.linkUrlOfSplashView)).apply {
        setPackage(applicationId)
        addCategory(intentCategoryBrowsable)
    }

    fun createMainViewIntent() = Intent(intentActionView, Uri.parse(BuildConfig.linkUrlOfMainView)).apply {
        setPackage(applicationId)
        addCategory(intentCategoryBrowsable)
    }

    fun createGpsSearchViewIntent() = Intent(intentActionView, Uri.parse(BuildConfig.linkUrlOfGpsSearchView)).apply {
        setPackage(applicationId)
        addCategory(intentCategoryBrowsable)
    }
}