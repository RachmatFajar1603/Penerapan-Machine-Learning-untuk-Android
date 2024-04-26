package com.dicoding.asclepius.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.injection.Injection
import com.dicoding.asclepius.model.HistoryViewModel
import com.dicoding.asclepius.model.ResultViewModel
import com.dicoding.asclepius.repository.HistoryRepository

class ViewModelFactory private constructor(private val historyRepository: HistoryRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(historyRepository) as T
        } else {
            return ResultViewModel(historyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context))
        }.also { instance = it }
    }
}