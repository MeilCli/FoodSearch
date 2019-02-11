package net.meilcli.foodsearch

import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineScope
import kotlin.properties.ReadWriteProperty

internal typealias IReadWriteProperty<TReceiver, TType> = ReadWriteProperty<TReceiver, TType>
internal typealias IJsonAdapterFactory = JsonAdapter.Factory
internal typealias ICoroutineScope = CoroutineScope
internal typealias IList<T> = List<T>