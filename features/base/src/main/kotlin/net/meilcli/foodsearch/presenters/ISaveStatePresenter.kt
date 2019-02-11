package net.meilcli.foodsearch.presenters

import android.os.Bundle

interface ISaveStatePresenter : IPresenter {

    fun onSaveState(bundle: Bundle)

    fun onRestoreState(bundle: Bundle)
}