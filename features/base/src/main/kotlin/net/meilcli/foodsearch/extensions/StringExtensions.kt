package net.meilcli.foodsearch.extensions

import java.net.URLDecoder
import java.net.URLEncoder

fun String.toUrlEncodedValue(): String {
    return URLEncoder.encode(this, "UTF-8")
}

fun String.toUrlDecodedValue(): String {
    return URLDecoder.decode(this, "UTF-8")
}