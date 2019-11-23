package org.dagger.mvp.di.modules

import android.content.Context
import androidx.room.Room
//import androidx.room.Room
//import com.ost.avtomaktab.db.dao.AvtoDao
//import com.ost.avtomaktab.db.database.AppDatabase
import dagger.Module
import dagger.Provides
import org.dagger.mvp.db.AppDao
import org.dagger.mvp.db.AppDatabase
import javax.inject.Singleton

@Module
class AvtoDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "applicationdb")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(database: AppDatabase): AppDao = database.appDao()
}