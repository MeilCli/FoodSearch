package net.meilcli.foodsearch

import com.squareup.moshi.JsonAdapter
import kotlin.properties.ReadWriteProperty

internal typealias IReadWriteProperty<TReceiver, TType> = ReadWriteProperty<TReceiver, TType>
internal typealias IJsonAdapterFactory = JsonAdapter.Factory