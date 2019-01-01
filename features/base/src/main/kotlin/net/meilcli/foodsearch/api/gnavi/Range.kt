package net.meilcli.foodsearch.api.gnavi

enum class Range(val queryValue: String) {
    M300("1"),
    M500("2"),
    M1000("3"),
    M2000("4"),
    M3000("5")
}