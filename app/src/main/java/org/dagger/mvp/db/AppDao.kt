package org.dagger.mvp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import org.dagger.mvp.model.Region

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRegions(region: ArrayList<Region>): Completable

    @Query("SELECT*FROM region")
    fun getRegions(): Observable<List<Region>>
}