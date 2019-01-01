package net.meilcli.foodsearch.api.gnavi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import net.meilcli.foodsearch.IJsonAdapterFactory
import net.meilcli.foodsearch.api.gnavi.adapters.*
import net.meilcli.foodsearch.api.gnavi.entities.*

import java.lang.reflect.Type
import java.util.*

object GnaviJsonAdapterFactory : IJsonAdapterFactory {

    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        return when (type) {
            Date::class.java -> Rfc3339DateJsonAdapter()
            Error::class.java -> ErrorJsonAdapter(moshi)
            Restaurant::class.java -> RestaurantJsonAdapter(moshi)
            RestaurantSearchResponse::class.java -> RestaurantSearchResponseJsonAdapter(moshi)
            ForeignRestaurant::class.java -> ForeignRestaurantJsonAdapter(moshi)
            ForeignRestaurantSearchResponse::class.java -> ForeignRestaurantSearchResponseJsonAdapter(moshi)
            PrefectureArea::class.java -> PrefectureAreaJsonAdapter(moshi)
            PrefectureAreaResponse::class.java -> PrefectureAreaResponseJsonAdapter(moshi)
            LargeArea::class.java -> LargeAreaJsonAdapter(moshi)
            LargeAreaResponse::class.java -> LargeAreaResponseJsonAdapter(moshi)
            MiddleArea::class.java -> MiddleAreaJsonAdapter(moshi)
            MiddleAreaResponse::class.java -> MiddleAreaResponseJsonAdapter(moshi)
            SmallArea::class.java -> SmallAreaJsonAdapter(moshi)
            SmallAreaResponse::class.java -> SmallAreaResponseJsonAdapter(moshi)
            LargeCategory::class.java -> LargeCategoryJsonAdapter(moshi)
            LargeCategoryResponse::class.java -> LargeCategoryResponseJsonAdapter(moshi)
            SmallCategory::class.java -> SmallCategoryJsonAdapter(moshi)
            SmallCategoryResponse::class.java -> SmallCategoryResponseJsonAdapter(moshi)
            else -> create(
                type,
                annotations,
                moshi,
                RestaurantJsonAdapter.InternalJsonAdapterFactory::create,
                ForeignRestaurantJsonAdapter.InternalJsonAdapterFactory::create,
                LargeAreaJsonAdapter.InternalJsonAdapterFactory::create,
                MiddleAreaJsonAdapter.InternalJsonAdapterFactory::create,
                SmallAreaJsonAdapter.InternalJsonAdapterFactory::create
            )
        }
    }

    private fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi,
        vararg createDelegate: (Type, MutableSet<out Annotation>, Moshi) -> JsonAdapter<*>?
    ): JsonAdapter<*>? {
        var jsonAdapter: JsonAdapter<*>? = null
        for (delegate in createDelegate) {
            jsonAdapter = delegate(type, annotations, moshi)
            if (jsonAdapter != null) {
                break
            }
        }
        return jsonAdapter
    }
}