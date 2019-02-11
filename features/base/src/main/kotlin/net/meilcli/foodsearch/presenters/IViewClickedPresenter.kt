package net.meilcli.foodsearch.presenters

import net.meilcli.foodsearch.views.IView

interface IViewClickedPresenter : IPresenter {

    fun onViewClicked(view: IView, clickedViewId: Int)
}