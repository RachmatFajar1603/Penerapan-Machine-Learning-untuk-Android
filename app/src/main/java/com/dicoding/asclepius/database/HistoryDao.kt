package com.dicoding.asclepius.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * FROM bookmark_data")
    fun getAllHistoryData(): LiveData<List<History>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistory(bookmark: History)

    @Query("DELETE FROM bookmark_data WHERE image = :name")
    suspend fun deleteHistory(name: String)

    @Query("SELECT * FROM bookmark_data WHERE image = :name")
    fun getHistoryData(name: String): LiveData<History>
}