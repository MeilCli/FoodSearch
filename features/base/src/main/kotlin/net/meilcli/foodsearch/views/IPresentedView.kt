package net.meilcli.foodsearch.views

import net.meilcli.foodsearch.presenters.IPresenter

interface IPresentedView : IView {

    val presenterCount: Int

    fun addPresenter(presenter: IPresenter)

    fun removePresenter(presenter: IPresenter)

    fun containPresenter(presenter: IPresenter): Boolean

    fun getPresenters(): Sequence<IPresenter>
}