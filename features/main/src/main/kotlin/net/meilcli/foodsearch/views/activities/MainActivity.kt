package net.meilcli.foodsearch.views.activities

import kotlinx.android.synthetic.main.activity_main.*
import net.meilcli.foodsearch.Launcher
import net.meilcli.foodsearch.R
import net.meilcli.foodsearch.presenters.IPresenter
import net.meilcli.foodsearch.views.IMainView
import android.content.Intent.ACTION_VIEW as intentActionView
import android.content.Intent.CATEGORY_BROWSABLE as intentCategoryBrowsable

class MainActivity : BaseActivity(), IMainView {

    override fun onCreateContentView() {
        setContentView(R.layout.activity_main)
    }

    override fun onCreatePresenters(): Sequence<IPresenter> {
        return emptySequence()
    }

    override fun onCreatedContentView() {
        super.onCreatedContentView()

        text.setOnClickListener {
            startActivity(Launcher.createGpsSearchViewIntent())
        }
    }
}
