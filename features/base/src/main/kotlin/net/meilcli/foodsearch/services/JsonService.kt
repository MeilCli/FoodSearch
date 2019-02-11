package net.meilcli.foodsearch.services

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import net.meilcli.foodsearch.IList
import net.meilcli.foodsearch.api.gnavi.GnaviJsonAdapterFactory
import net.meilcli.foodsearch.api.gnavi.entities.LargeCategory

class JsonService : IJsonService {

    private val moshi = Moshi.Builder().add(GnaviJsonAdapterFactory).build()

    private val largeCategoriesJsonAdapter by lazy {
        moshi.adapter<IList<LargeCategory>>(
            Types.newParameterizedType(
                IList::class.java,
                LargeCategory::class.java
            )
        )
    }

    override fun toLargeCategoriesJson(value: IList<LargeCategory>): String {
        return largeCategoriesJsonAdapter.toJson(value)
    }

    override fun toLargeCategoriesValue(json: String): IList<LargeCategory>? {
        return largeCategoriesJsonAdapter.fromJson(json)
    }
}