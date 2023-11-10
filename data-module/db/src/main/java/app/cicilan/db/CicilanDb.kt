package app.cicilan.db

import androidx.room.Database
import androidx.room.RoomDatabase
import app.cicilan.entities.ItemEntity
import app.cicilan.entities.ItemLogEntity

@Database(
    entities = [
        ItemEntity::class,
        ItemLogEntity::class,
    ],
    version = 2,
    exportSchema = false,
)
abstract class CicilanDb : RoomDatabase() {
    abstract fun cicilanDao(): CicilanDao
}
