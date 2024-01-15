package app.cicilan.provider

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import app.cicilan.db.DbProvider

/**
 * Created by: Muhammad Jafar
 * At: 02 Dec 23
 * Find me: 131.powerfull@gmail.com
 */

class RoomDbProvider(private val context: Context) : DbProvider() {
    override fun <T : RoomDatabase> createDatabase(name: String, clazz: Class<T>): T =
        Room.databaseBuilder(context, clazz, name).build()
}
