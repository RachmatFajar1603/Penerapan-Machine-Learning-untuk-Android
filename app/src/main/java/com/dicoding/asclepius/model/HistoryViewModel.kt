package com.dicoding.asclepius.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.repository.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val historyRepository: HistoryRepository) : ViewModel() {

    fun getAllHistoryData() = historyRepository.getAllHistoryData()

}