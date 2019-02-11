package net.meilcli.foodsearch.views

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import net.meilcli.foodsearch.api.gnavi.entities.ICategory

interface ISearchQueryView : IView {

    // Category

    val categoryFailedRetryButtonId: Int

    fun showCategoryProgressState()

    fun showCategorySuccessState()

    fun showCategoryFailedState()

    fun setCategoryFailedText(message: String)

    fun addCategory(category: ICategory)

    fun removeCategories(condition: (ICategory) -> Boolean)

    fun clearCategory()

    fun getCheckedCategories(): Sequence<ICategory>

    // Option

    fun addOption(@StringRes description: Int, @DrawableRes icon: Int?)
}