package app.cicilan.repositories.repository

import app.cicilan.local.preference.StoreData
import app.cicilan.repositories.contracts.SettingRepository
import kotlinx.coroutines.flow.Flow


class SettingRepoImpl(private val store: StoreData) : SettingRepository {
    override fun getTheme(): Flow<Int> =
        store.getTheme()

    override fun getLang(): Flow<String> =
        store.getLanguage()

    override suspend fun saveTheme(theme: Int) {
        store.saveTheme(theme)
    }

    override suspend fun saveLang(lang: String) {
        store.saveLanguage(lang)
    }

}
