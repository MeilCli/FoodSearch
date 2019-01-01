package net.meilcli.foodsearch.api.gnavi.queries

import net.meilcli.foodsearch.IReadWriteProperty
import net.meilcli.foodsearch.api.IQuery
import net.meilcli.foodsearch.api.IRequestQuery
import net.meilcli.foodsearch.api.gnavi.Language
import kotlin.reflect.KProperty

class LanguageQuery(override val name: String) : IQuery, IReadWriteProperty<IRequestQuery, Language> {

    companion object {

        private val defaultValue = Language.Japanese
    }

    private var value = defaultValue

    override fun hasValue(): Boolean {
        return value != defaultValue
    }

    override fun toValue(): String {
        return value.queryValue
    }

    override fun getValue(thisRef: IRequestQuery, property: KProperty<*>): Language {
        return value
    }

    override fun setValue(thisRef: IRequestQuery, property: KProperty<*>, value: Language) {
        this.value = value

        if (value == defaultValue) {
            thisRef.queries.remove(name)
            return
        }

        thisRef.queries[name] = this
    }
}