package net.meilcli.foodsearch.api.gnavi.queries

import net.meilcli.foodsearch.IReadWriteProperty
import net.meilcli.foodsearch.api.IQuery
import net.meilcli.foodsearch.api.IRequestQuery
import net.meilcli.foodsearch.api.gnavi.Range
import kotlin.reflect.KProperty

class RangeQuery(override val name: String) : IQuery, IReadWriteProperty<IRequestQuery, Range> {

    companion object {

        private val defaultValue = Range.M500
    }

    private var value = defaultValue

    override fun hasValue(): Boolean {
        return value != defaultValue
    }

    override fun toValue(): String {
        return value.queryValue
    }

    override fun getValue(thisRef: IRequestQuery, property: KProperty<*>): Range {
        return value
    }

    override fun setValue(thisRef: IRequestQuery, property: KProperty<*>, value: Range) {
        this.value = value

        if (value == defaultValue) {
            thisRef.queries.remove(name)
            return
        }

        thisRef.queries[name] = this
    }
}