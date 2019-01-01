package net.meilcli.foodsearch.api.gnavi.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import net.meilcli.foodsearch.api.createNonNullJsonDataException
import net.meilcli.foodsearch.api.createNullPointerExceptionWhenConvertJson
import net.meilcli.foodsearch.api.createRequirePropertyJsonDataException
import net.meilcli.foodsearch.api.gnavi.entities.Error

internal class ErrorJsonAdapter(moshi: Moshi) : JsonAdapter<Error>() {

    /**
     * {
     *     "@attributes": {
     *         "api_version": "v3"
     *     },
     *     "error": [
     *         {
     *             "code": 400,
     *             "message": ""
     *         }
     *     ]
     * }
     */

    companion object {

        private const val errorKey = "error"
        private const val messageKey = "message"
        private const val codeKey = "code"
    }

    private val errorOptions = JsonReader.Options.of(errorKey)
    private val elementOptions = JsonReader.Options.of(messageKey, codeKey)
    private val stringAdapter = moshi.adapter(String::class.java)
    private val integerAdapter = moshi.adapter(Int::class.java)

    override fun fromJson(reader: JsonReader): Error {
        var error: Error? = null

        reader.beginObject()

        while (reader.hasNext()) {
            when (reader.selectName(errorOptions)) {
                0 -> {
                    reader.beginArray()
                    reader.beginObject()

                    error = errorFromJson(reader)

                    reader.endObject()
                    reader.endArray()
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        return error ?: throw createRequirePropertyJsonDataException(errorKey, reader)
    }

    private fun errorFromJson(reader: JsonReader): Error {
        var message: String? = null
        var code: Int? = null

        while (reader.hasNext()) {
            when (reader.selectName(elementOptions)) {
                0 -> message = stringAdapter.fromJson(reader) ?:
                        throw createNonNullJsonDataException(messageKey, reader)
                1 -> code = integerAdapter.fromJson(reader) ?: throw createNonNullJsonDataException(codeKey, reader)
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }

        return Error(
            message = message ?: throw createRequirePropertyJsonDataException(messageKey, reader),
            code = code ?: throw createRequirePropertyJsonDataException(codeKey, reader)
        )
    }

    override fun toJson(writer: JsonWriter, value: Error?) {
        value ?: throw createNullPointerExceptionWhenConvertJson()

        writer.beginObject()
        writer.name(errorKey)

        writer.beginArray()
        writer.beginObject()
        writer.name(messageKey)
        stringAdapter.toJson(writer, value.message)
        writer.name(codeKey)
        integerAdapter.toJson(writer, value.code)
        writer.endObject()
        writer.endArray()

        writer.endObject()
    }
}