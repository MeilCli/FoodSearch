package net.meilcli.foodsearch.extensions

import net.meilcli.foodsearch.presenters.IPresenter
import net.meilcli.foodsearch.views.IPresentedView

inline fun IPresentedView.forEachPresenters(action: (IPresenter) -> Unit) {
    for (presenter in getPresenters()) {
        action(presenter)
    }
}

inline fun <reified T> IPresentedView.forEachPresentersOfInstance(action: (T) -> Unit) {
    for (presenter in getPresenters().filterIsInstance<T>()) {
        action(presenter)
    }
}