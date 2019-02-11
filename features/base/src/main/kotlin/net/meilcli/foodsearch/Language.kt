package net.meilcli.foodsearch

enum class Language(val value: String) {

    Japanese(BuildConfig.languageJapanese) {
        override fun toGnaviLanguage(): net.meilcli.foodsearch.api.gnavi.Language {
            return net.meilcli.foodsearch.api.gnavi.Language.Japanese
        }
    },

    English(BuildConfig.languageEnglish) {
        override fun toGnaviLanguage(): net.meilcli.foodsearch.api.gnavi.Language {
            return net.meilcli.foodsearch.api.gnavi.Language.English
        }
    };

    abstract fun toGnaviLanguage(): net.meilcli.foodsearch.api.gnavi.Language
}