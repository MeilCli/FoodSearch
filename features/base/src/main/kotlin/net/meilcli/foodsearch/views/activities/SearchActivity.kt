package net.meilcli.foodsearch.views.activities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.GridLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_category.view.*
import kotlinx.android.synthetic.main.content_category_failed.view.*
import kotlinx.android.synthetic.main.content_category_progress.view.*
import kotlinx.android.synthetic.main.content_option.view.*
import kotlinx.android.synthetic.main.content_search_query.*
import kotlinx.android.synthetic.main.content_search_query.view.*
import net.meilcli.foodsearch.R
import net.meilcli.foodsearch.api.gnavi.entities.ICategory
import net.meilcli.foodsearch.extensions.addLineChipTerminatorHandler
import net.meilcli.foodsearch.extensions.addSpaceChipTerminatorHandler
import net.meilcli.foodsearch.extensions.getChildViews
import net.meilcli.foodsearch.presenters.IPresenter
import net.meilcli.foodsearch.presenters.SearchQueryPresenter
import net.meilcli.foodsearch.views.ISearchQueryView
import net.meilcli.foodsearch.views.custom.StateView
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT as wrapContent

@SuppressLint("Registered")
open class SearchActivity : BaseActivity(), ISearchQueryView {

    override val categoryFailedRetryButtonId = R.id.categoryFailedButton

    override fun onCreateContentView() {
        setContentView(R.layout.activity_search)

        searchWords.addSpaceChipTerminatorHandler()
        searchWords.addLineChipTerminatorHandler()

        val behavior = BottomSheetBehavior.from(searchQueryBottomSheet)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreatePresenters(): Sequence<IPresenter> {
        return sequence {
            yield(
                SearchQueryPresenter(
                    foodSearchApplication.gnaviService,
                    foodSearchApplication.jsonService,
                    foodSearchApplication.configurationService
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()
        categoryView.progressLayout?.categoryShimmerLayout?.startShimmerAnimation()
    }

    override fun showCategoryProgressState() {
        categoryView.updateState(StateView.State.Progress)
    }

    override fun showCategorySuccessState() {
        categoryView.updateState(StateView.State.Success)
    }

    override fun showCategoryFailedState() {
        categoryView.updateState(StateView.State.Failed)
    }

    override fun setCategoryFailedText(message: String) {
        categoryView.failedLayout?.categoryFailedText?.text = message
    }

    override fun removeCategories(condition: (ICategory) -> Boolean) {
        val categoryContainer = categoryView.successLayout?.categoryChipGroup ?: return

        for (child in categoryContainer.getChildViews()) {
            val category = child.tag as? ICategory ?: continue
            if (condition(category)) {
                categoryContainer.removeView(child)
            }
        }
    }

    override fun clearCategory() {
        categoryView.successLayout?.categoryChipGroup?.removeAllViews()
    }

    override fun getCheckedCategories(): Sequence<ICategory> {
        val categoryContainer = categoryView.successLayout?.categoryChipGroup ?: return emptySequence()

        return sequence {
            for (child in categoryContainer.getChildViews()) {
                val chip = child as? Chip ?: continue
                val category = chip.tag as? ICategory ?: continue

                if (chip.isChecked) {
                    yield(category)
                }
            }
        }
    }

    override fun addCategory(category: ICategory) {
        val chip =
            LayoutInflater.from(this).inflate(R.layout.view_chip_filter, null) as? Chip ?: throw IllegalStateException()
        chip.text = category.name
        chip.tag = category
        categoryView.successLayout?.categoryChipGroup?.addView(chip)
    }

    override fun addOption(description: Int, icon: Int?) {
        val optionView = LayoutInflater.from(this).inflate(R.layout.content_option, categoryView.optionContainer, false)

        optionView.optionText.setText(description)
        if (icon != null) {
            optionView.optionIcon.setImageResource(icon)
        }
        optionView.layoutParams = GridLayout.LayoutParams().apply {
            width = 0
            height = wrapContent
            columnSpec = GridLayout.spec(optionContainer.childCount % optionContainer.columnCount, 1F)
        }

        optionContainer.addView(optionView)
    }

    override fun onPause() {
        super.onPause()
        categoryView.progressLayout?.categoryShimmerLayout?.stopShimmerAnimation()
    }

}