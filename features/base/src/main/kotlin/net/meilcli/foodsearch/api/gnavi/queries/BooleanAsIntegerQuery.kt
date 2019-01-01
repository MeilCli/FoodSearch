package net.meilcli.foodsearch.api.gnavi.queries

import net.meilcli.foodsearch.IReadWriteProperty
import net.meilcli.foodsearch.api.IQuery
import net.meilcli.foodsearch.api.IRequestQuery
import kotlin.reflect.KProperty

class BooleanAsIntegerQuery(override val name: String) : IQuery, IReadWriteProperty<IRequestQuery, Boolean> {

    companion object {

        private const val defaultValue = false
        private const val trueQueryValue = 1
        private const val falseQueryValue = 0
    }

    private var value = defaultValue

    override fun hasValue(): Boolean {
        return value != defaultValue
    }

    override fun toValue(): String {
        return if (value) {
            trueQueryValue.toString()
        } else {
            falseQueryValue.toString()
        }
    }

    override fun getValue(thisRef: IRequestQuery, property: KProperty<*>): Boolean {
        return value
    }

    override fun setValue(thisRef: IRequestQuery, property: KProperty<*>, value: Boolean) {
        this.value = value

        if (value == defaultValue) {
            thisRef.queries.remove(name)
            return
        }

        thisRef.queries[name] = this
    }
}