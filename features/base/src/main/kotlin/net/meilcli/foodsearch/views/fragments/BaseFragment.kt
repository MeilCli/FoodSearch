package net.meilcli.foodsearch.views.fragments

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import net.meilcli.foodsearch.IFoodSearchApplication
import net.meilcli.foodsearch.presenters.ILifecyclePresenter
import net.meilcli.foodsearch.presenters.IPresenter
import net.meilcli.foodsearch.views.IPresentedView

abstract class BaseFragment : Fragment(), IPresentedView {

    override val foodSearchApplication: IFoodSearchApplication
        get() = context?.applicationContext as IFoodSearchApplication

    private val presenters = mutableListOf<IPresenter>()

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

    private inline fun forEachPresenters(action: (IPresenter) -> Unit) {
        for (presenter in presenters) {
            action(presenter)
        }
    }

    private inline fun <reified T> forEachPresentersOfInstance(action: (T) -> Unit) {
        for (presenter in presenters.asSequence().filterIsInstance<T>()) {
            action(presenter)
        }
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        for (presenter in onCreatePresenters()) {
            addPresenter(presenter)
        }

        onCreatedPresenters()
    }

    abstract fun onCreatePresenters(): Sequence<IPresenter>

    @CallSuper
    open fun onCreatedPresenters() {
        forEachPresenters { it.onCreate() }
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // wait for activity create
        forEachPresentersOfInstance<ILifecyclePresenter> { it.onCreatedView(this) }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()

        forEachPresentersOfInstance<ILifecyclePresenter> { it.onStartView(this) }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        forEachPresentersOfInstance<ILifecyclePresenter> { it.onResumeView(this) }
    }

    @CallSuper
    override fun onPause() {
        super.onPause()

        forEachPresentersOfInstance<ILifecyclePresenter> { it.onPauseView(this) }
    }

    @CallSuper
    override fun onStop() {
        super.onStop()

        forEachPresentersOfInstance<ILifecyclePresenter> { it.onStopView(this) }
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()

        forEachPresentersOfInstance<ILifecyclePresenter> { it.onDestroyView(this) }
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()

        forEachPresenters { it.onDestroy() }
    }
}