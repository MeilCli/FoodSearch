package net.meilcli.foodsearch.presenters

import android.os.Bundle
import kotlinx.coroutines.*
import net.meilcli.foodsearch.IList
import net.meilcli.foodsearch.Language
import net.meilcli.foodsearch.api.gnavi.entities.LargeCategory
import net.meilcli.foodsearch.coroutines.ioDispatcher
import net.meilcli.foodsearch.coroutines.mainDispatcher
import net.meilcli.foodsearch.services.IConfigurationService
import net.meilcli.foodsearch.services.IGnaviService
import net.meilcli.foodsearch.services.IJsonService
import net.meilcli.foodsearch.views.ISearchQueryView
import net.meilcli.foodsearch.views.IView

class SearchQueryPresenter(
    private val gnaviService: IGnaviService,
    private val jsonService: IJsonService,
    private val configurationService: IConfigurationService
) : BasePresenter(), ISearchQueryPresenter {

    companion object {

        private const val largeCategoriesStateKey = "large_categories"
        private const val languageStateKey = "language"
    }

    private val onDestroyViewContext = SupervisorJob() + mainDispatcher

    private val requestModel = gnaviService.createRestaurantRequestModel()
    private var largeCategories: IList<LargeCategory>? = null

    override fun onRestoreState(bundle: Bundle) {
        val savedLanguage = Language.values()[bundle.getInt(languageStateKey)]
        if (bundle.containsKey(largeCategoriesStateKey) && savedLanguage == configurationService.currentLanguage) {
            val bundleValue = bundle.getString(largeCategoriesStateKey) ?: return
            largeCategories = jsonService.toLargeCategoriesValue(bundleValue)
        }
    }

    override fun onCreatedView(view: IView) {
        if (view !is ISearchQueryView) return

        setUpOptions(view)
    }

    private fun setUpOptions(view: ISearchQueryView) {
        for (option in requestModel.createOptions()) {
            view.addOption(option.description, option.icon)
        }
    }

    override fun onResumeView(view: IView) {
        if (view !is ISearchQueryView) return

        if (largeCategories == null) {
            loadCategories(view)
        }
    }

    private fun loadCategories(view: ISearchQueryView) = launch(onDestroyViewContext) {
        val largeCategoriesTask = async(ioDispatcher) { gnaviService.getLargeCategories() }

        withContext(mainDispatcher) {
            try {
                view.showCategoryProgressState()

                largeCategories = largeCategoriesTask.await()

                view.clearCategory()
                largeCategories?.forEach { view.addCategory(it) }
                view.showCategorySuccessState()
            } catch (exception: Exception) {
                view.showCategoryFailedState()
                view.setCategoryFailedText(exception.message ?: "error")
            }
        }
    }

    override fun onViewClicked(view: IView, clickedViewId: Int) {
        if (view !is ISearchQueryView) return

        when (clickedViewId) {
            view.categoryFailedRetryButtonId -> loadCategories(view)
        }
    }

    override fun onSaveState(bundle: Bundle) {
        largeCategories?.also {
            bundle.putString(largeCategoriesStateKey, jsonService.toLargeCategoriesJson(it))
        }
        bundle.putInt(languageStateKey, configurationService.currentLanguage.ordinal)
    }

    override fun onDestroyView(view: IView) {
        onDestroyViewContext.cancel()
    }

    override fun onStartView(view: IView) {
    }

    override fun onPauseView(view: IView) {
    }

    override fun onStopView(view: IView) {
    }
}