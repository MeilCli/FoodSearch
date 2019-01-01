package net.meilcli.foodsearch.api.queries

import net.meilcli.foodsearch.IReadWriteProperty
import net.meilcli.foodsearch.api.IQuery
import net.meilcli.foodsearch.api.IRequestQuery
import kotlin.reflect.KProperty

class DoubleQuery(override val name: String) : IQuery, IReadWriteProperty<IRequestQuery, Double?> {

    companion object {

        private val defaultValue = null
    }

    private var value: Double? = defaultValue

    override fun hasValue(): Boolean {
        return value != defaultValue
    }

    override fun toValue(): String {
        val value = requireNotNull(value)
        return value.toString()
    }

    override fun getValue(thisRef: IRequestQuery, property: KProperty<*>): Double? {
        return value
    }

    override fun setValue(thisRef: IRequestQuery, property: KProperty<*>, value: Double?) {
        this.value = value

        if (value == defaultValue) {
            thisRef.queries.remove(name)
            return
        }

        thisRef.queries[name] = this
    }
}