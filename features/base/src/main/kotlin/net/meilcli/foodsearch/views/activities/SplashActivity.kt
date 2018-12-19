package net.meilcli.foodsearch.views.activities

import com.google.android.instantapps.InstantApps
import net.meilcli.foodsearch.Launcher
import net.meilcli.foodsearch.presenters.IPresenter

class SplashActivity : BaseActivity() {

    override fun onCreateContentView() {
        if (InstantApps.isInstantApp(this)) {
            startActivity(Launcher.createGpsSearchViewIntent())
        } else {
            startActivity(Launcher.createMainViewIntent())
        }
        finish()
    }

    override fun onCreatePresenters(): Sequence<IPresenter> {
        return emptySequence()
    }

}