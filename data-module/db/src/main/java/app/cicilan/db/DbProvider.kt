package app.cicilan.db

import androidx.room.RoomDatabase

/**
 * Created by: Muhammad Jafar
 * At: 02 Dec 23
 * Find me: 131.powerfull@gmail.com
 */

abstract class DbProvider {
    private val database = HashMap<String, RoomDatabase>()

    protected abstract fun <T : RoomDatabase> createDatabase(name: String, clazz: Class<T>): T

    fun <T : RoomDatabase> provide(
        name: String,
        clazz: Class<T>,
    ): T = synchronized(this) {
        this.getDatabaseInstance(name) ?: this.createDatabase(name, clazz)
            .also { db -> database[name] = db }
    }

    private fun <T : RoomDatabase> getDatabaseInstance(name: String): T? = database[name] as? T
}
