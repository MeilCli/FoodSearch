package net.meilcli.foodsearch.presenters

import net.meilcli.foodsearch.views.IView

interface ILifecyclePresenter : IPresenter {

    fun onCreatedView(view: IView)

    fun onStartView(view: IView)

    fun onResumeView(view: IView)

    fun onPauseView(view: IView)

    fun onStopView(view: IView)

    fun onDestroyView(view: IView)
}