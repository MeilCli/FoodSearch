package net.meilcli.foodsearch.presenters

import kotlinx.coroutines.cancel
import net.meilcli.foodsearch.ICoroutineScope
import net.meilcli.foodsearch.coroutines.mainScope

open class BasePresenter : IPresenter, ICoroutineScope by mainScope() {

    override fun onCreate() {
    }

    override fun onDestroy() {
        coroutineContext.cancel()
    }
}