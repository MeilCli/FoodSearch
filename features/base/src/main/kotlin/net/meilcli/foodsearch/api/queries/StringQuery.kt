package net.meilcli.foodsearch.api.queries

import net.meilcli.foodsearch.IReadWriteProperty
import net.meilcli.foodsearch.api.IQuery
import net.meilcli.foodsearch.api.IRequestQuery
import net.meilcli.foodsearch.extensions.toUrlEncodedValue
import kotlin.reflect.KProperty

class StringQuery(override val name: String) : IQuery, IReadWriteProperty<IRequestQuery, String?> {

    companion object {

        private val defaultValue = null
    }

    private var value: String? = defaultValue

    override fun hasValue(): Boolean {
        return value != defaultValue
    }

    override fun toValue(): String {
        val value = requireNotNull(value)
        return value.toUrlEncodedValue()
    }

    override fun getValue(thisRef: IRequestQuery, property: KProperty<*>): String? {
        return value
    }

    override fun setValue(thisRef: IRequestQuery, property: KProperty<*>, value: String?) {
        this.value = value

        if (value == defaultValue) {
            thisRef.queries.remove(name)
            return
        }

        thisRef.queries[name] = this
    }
}