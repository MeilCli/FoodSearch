package net.meilcli.foodsearch.api.gnavi

import net.meilcli.foodsearch.api.gnavi.entities.Error

class GnaviApiException(val error: Error) : Exception()