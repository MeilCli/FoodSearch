package net.meilcli.foodsearch.views.activities

import android.content.Intent
import android.net.Uri
import kotlinx.android.synthetic.main.activity_main.*
import net.meilcli.foodsearch.R
import net.meilcli.foodsearch.presenters.IPresenter
import net.meilcli.foodsearch.views.IMainView
import android.content.Intent.ACTION_VIEW as intentActionView

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
            val intent = Intent(intentActionView, Uri.parse("https://foodsearch.meilcli.net/app/gps_search")).apply {
                addCategory(Intent.CATEGORY_BROWSABLE)
            }
            startActivity(intent)
        }
    }
}
