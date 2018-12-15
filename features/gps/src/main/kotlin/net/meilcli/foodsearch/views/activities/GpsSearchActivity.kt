package net.meilcli.foodsearch.views.activities

import net.meilcli.foodsearch.R
import net.meilcli.foodsearch.presenters.IPresenter
import net.meilcli.foodsearch.views.IGpsSearchView

class GpsSearchActivity : BaseActivity(), IGpsSearchView {

    override fun onCreateContentView() {
        setContentView(R.layout.activity_search)
    }

    override fun onCreatePresenters(): Sequence<IPresenter> {
        return emptySequence()
    }
}
