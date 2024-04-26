package com.dicoding.asclepius.repository

import androidx.lifecycle.LiveData
import com.dicoding.asclepius.database.History
import com.dicoding.asclepius.database.HistoryDao

class HistoryRepository private constructor(
    private val historyDao: HistoryDao,
) {
    fun getAllHistoryData(): LiveData<List<History>> = historyDao.getAllHistoryData()

    fun getHistoryData(name: String): LiveData<History> {
        return historyDao.getHistoryData(name)
    }

    suspend fun insert(history: History) {
        historyDao.insertHistory(history)
    }

    suspend fun delete(name: String) {
        historyDao.deleteHistory(name)
    }

    companion object {
        @Volatile
        private var instance: HistoryRepository? = null
        fun getInstance(
            historyDao: HistoryDao,
        ): HistoryRepository =
            instance ?: synchronized(this) {
                instance ?: HistoryRepository(historyDao)
            }.also { instance = it }
    }
}