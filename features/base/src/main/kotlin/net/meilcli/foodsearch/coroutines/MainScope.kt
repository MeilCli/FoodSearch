package net.meilcli.foodsearch.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

fun mainScope() = CoroutineScope(SupervisorJob() + mainDispatcher)