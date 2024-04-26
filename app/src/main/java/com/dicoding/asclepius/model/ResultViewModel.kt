package com.dicoding.asclepius.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.database.History
import com.dicoding.asclepius.repository.HistoryRepository
import kotlinx.coroutines.launch

class ResultViewModel(private val historyRepository: HistoryRepository) : ViewModel() {

    // Fungsi untuk mengambil data History berdasarkan gambar
    fun fetchHistoryData(image: String) = historyRepository.getHistoryData(image)

    // Fungsi untuk menyimpan data History baru ke dalam database
    fun insert(image: String, label: String, score: String) {
        // Menggunakan viewModelScope untuk meluncurkan coroutine
        viewModelScope.launch {
            val history = History(
                image = image, label = label, score = score
            )
            historyRepository.insert(history)
        }
    }
}