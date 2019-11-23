package org.dagger.mvp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.dagger.mvp.model.Region

@Database(entities = [Region::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao():AppDao
}