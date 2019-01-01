package net.meilcli.foodsearch.api.queries

import net.meilcli.foodsearch.IReadWriteProperty
import net.meilcli.foodsearch.api.IQuery
import net.meilcli.foodsearch.api.IRequestQuery
import kotlin.reflect.KProperty

class IntegerQuery(override val name: String) : IQuery, IReadWriteProperty<IRequestQuery, Int?> {

    companion object {

        private val defaultValue = null
    }

    private var value: Int? = defaultValue

    override fun hasValue(): Boolean {
        return value != defaultValue
    }

    override fun toValue(): String {
        val value = requireNotNull(value)
        return value.toString()
    }

    override fun getValue(thisRef: IRequestQuery, property: KProperty<*>): Int? {
        return value
    }

    override fun setValue(thisRef: IRequestQuery, property: KProperty<*>, value: Int?) {
        this.value = value

        if (value == defaultValue) {
            thisRef.queries.remove(name)
            return
        }

        thisRef.queries[name] = this
    }
}