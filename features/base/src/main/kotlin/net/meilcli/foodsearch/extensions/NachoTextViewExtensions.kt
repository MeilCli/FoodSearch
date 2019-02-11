package net.meilcli.foodsearch.extensions

import com.hootsuite.nachos.NachoTextView
import com.hootsuite.nachos.terminator.ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL as behaviorChipifyAllChipTerminatorHandler

fun NachoTextView.addSpaceChipTerminatorHandler() {
    addChipTerminator(' ', behaviorChipifyAllChipTerminatorHandler)
}

fun NachoTextView.addLineChipTerminatorHandler() {
    addChipTerminator('\n', behaviorChipifyAllChipTerminatorHandler)
}