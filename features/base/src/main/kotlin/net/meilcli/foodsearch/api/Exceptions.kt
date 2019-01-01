package net.meilcli.foodsearch.api

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader

internal fun createNonNullJsonDateException(reader: JsonReader): JsonDataException {
    return JsonDataException("Non-null value was null at ${reader.path}")
}

internal fun createNonNullJsonDataException(name: String, reader: JsonReader): JsonDataException {
    return JsonDataException("Non-null value '$name' was null at ${reader.path}")
}

internal fun createRequirePropertyJsonDataException(name: String, reader: JsonReader): JsonDataException {
    return JsonDataException("Required property '$name' missing at ${reader.path}")
}

internal fun createNullPointerExceptionWhenConvertJson(): NullPointerException {
    return NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
}

