package net.meilcli.foodsearch.views.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import net.meilcli.foodsearch.IFoodSearchApplication
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
        presenters.asSequence()
            .forEach { it.onCreate() }
    }

    @CallSuper
    open fun onCreatedContentView() {
        presenters.asSequence()
            .filterIsInstance<ILifecyclePresenter>()
            .forEach { it.onCreatedView(this) }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()

        presenters.asSequence()
            .filterIsInstance<ILifecyclePresenter>()
            .forEach { it.onStartView(this) }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        presenters.asSequence()
            .filterIsInstance<ILifecyclePresenter>()
            .forEach { it.onResumeView(this) }
    }

    @CallSuper
    override fun onPause() {
        super.onPause()

        presenters.asSequence()
            .filterIsInstance<ILifecyclePresenter>()
            .forEach { it.onPauseView(this) }
    }

    @CallSuper
    override fun onStop() {
        super.onStop()

        presenters.asSequence()
            .filterIsInstance<ILifecyclePresenter>()
            .forEach { it.onStopView(this) }
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()

        presenters.asSequence()
            .filterIsInstance<ILifecyclePresenter>()
            .forEach { it.onDestroyView(this) }

        presenters.asSequence()
            .forEach { it.onDestroy() }
    }
}