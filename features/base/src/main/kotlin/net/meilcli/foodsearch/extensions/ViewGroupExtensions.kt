package net.meilcli.foodsearch.extensions

import android.view.View
import android.view.ViewGroup

fun ViewGroup.getChildViews(): List<View> {
    val result = mutableListOf<View>()

    for (i in 0 until childCount) {
        result.add(getChildAt(i))
    }

    return result
}