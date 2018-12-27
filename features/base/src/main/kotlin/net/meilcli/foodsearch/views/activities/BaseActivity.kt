package net.meilcli.foodsearch.views.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import net.meilcli.foodsearch.IFoodSearchApplication
import net.meilcli.foodsearch.extensions.forEachPresenters
import net.meilcli.foodsearch.extensions.forEachPresentersOfInstance
import net.meilcli.foodsearch.presenters.ILifecyclePresenter
import net.meilcli.foodsearch.presenters.IPresenter
import net.meilcli.foodsearch.views.IPresentedView

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(), IPresentedView {

    private val presenters = mutableListOf<IPresenter>()

    override val foodSearchApplication: IFoodSearchApplication
        get() = application as IFoodSearchApplication

    override val presenterCount: Int
        get() = presenters.size

    override fun addPresenter(presenter: IPresenter) {
        presenters += presenter
    }

    override fun removePresenter(presenter: IPresenter) {
        presenters -= presenter
    }

    override fun containPresenter(presenter: IPresenter): Boolean {
        return presenters.contains(presenter)
    }

    override fun getPresenters(): Sequence<IPresenter> {
        return presenters.asSequence()
    }

    @CallSuper
    @Deprecated(
        "split lifecycle by BaseActivity",
        ReplaceWith("onCreateContentView()"),
        DeprecationLevel.ERROR
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onCreateContentView()

        for (presenter in onCreatePresenters()) {
            addPresenter(presenter)
        }

        onCreatedPresenters()
        onCreatedContentView()
    }

    /**
     * must call [setContentView]
     */
    abstract fun onCreateContentView()

    abstract fun onCreatePresenters(): Sequence<IPresenter>

    @CallSuper
    open fun onCreatedPresenters() {
        forEachPresenters { it.onCreate() }
    }

    @CallSuper
    open fun onCreatedContentView() {
        forEachPresentersOfInstance<ILifecyclePresenter> {
            it.onCreatedView(this)
        }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()

        forEachPresentersOfInstance<ILifecyclePresenter> {
            it.onStartView(this)
        }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        forEachPresentersOfInstance<ILifecyclePresenter> {
            it.onResumeView(this)
        }
    }

    @CallSuper
    override fun onPause() {
        super.onPause()

        forEachPresentersOfInstance<ILifecyclePresenter> {
            it.onPauseView(this)
        }
    }

    @CallSuper
    override fun onStop() {
        super.onStop()

        forEachPresentersOfInstance<ILifecyclePresenter> {
            it.onStopView(this)
        }
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()

        forEachPresentersOfInstance<ILifecyclePresenter> {
            it.onDestroyView(this)
        }
        forEachPresenters {
            it.onDestroy()
        }
    }
}