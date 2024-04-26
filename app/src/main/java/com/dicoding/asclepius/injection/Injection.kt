package com.dicoding.asclepius.injection

import android.content.Context
import com.dicoding.asclepius.database.HistoryDatabase
import com.dicoding.asclepius.repository.HistoryRepository

object Injection {
    fun provideRepository(context: Context): HistoryRepository {
        val database = HistoryDatabase.getInstance(context)
        val dao = database.historyDao()
        return HistoryRepository.getInstance(dao)
    }
}