package net.meilcli.foodsearch.api.queries

import net.meilcli.foodsearch.IReadWriteProperty
import net.meilcli.foodsearch.api.IQuery
import net.meilcli.foodsearch.api.IRequestQuery
import net.meilcli.foodsearch.extensions.toUrlEncodedValue
import kotlin.reflect.KProperty

class StringArrayQuery(override val name: String) : IQuery, IReadWriteProperty<IRequestQuery, Array<String>?> {

    companion object {

        private val defaultValue = null
    }

    private var value: Array<String>? = defaultValue

    override fun hasValue(): Boolean {
        return value != defaultValue
    }

    override fun toValue(): String {
        val value = requireNotNull(value)
        return value.asSequence()
            .map { it.toUrlEncodedValue() }
            .joinToString(",")
    }

    override fun getValue(thisRef: IRequestQuery, property: KProperty<*>): Array<String>? {
        return value
    }

    override fun setValue(thisRef: IRequestQuery, property: KProperty<*>, value: Array<String>?) {
        this.value = value

        if (value == defaultValue) {
            thisRef.queries.remove(name)
            return
        }

        thisRef.queries[name] = this
    }
}