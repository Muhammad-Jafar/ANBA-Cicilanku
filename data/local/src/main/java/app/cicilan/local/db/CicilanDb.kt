package app.cicilan.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.cicilan.entities.Item
import app.cicilan.entities.ItemLog

@Database(
    entities = [
        Item::class,
        ItemLog::class,
    ],
    version = 3,
    exportSchema = false
)
abstract class CicilanDb : RoomDatabase() {
    abstract fun cicilanDao(): CicilanDao
}

fun provideDatabase(context: Context) =
    Room
        .databaseBuilder(
            context = context,
            klass = CicilanDb::class.java,
            name = "cicilan",
        )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration(false)
        .build()

fun provideDao(db: CicilanDb) = db.cicilanDao()
