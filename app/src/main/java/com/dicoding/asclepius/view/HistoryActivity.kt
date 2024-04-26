package com.dicoding.asclepius.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.adapter.HistoryAdapter
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.factory.ViewModelFactory
import com.dicoding.asclepius.model.HistoryViewModel
import com.dicoding.asclepius.model.ResultViewModel
import kotlin.properties.ReadOnlyProperty

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val historyViewModel = ViewModelProvider(this, factory).get(HistoryViewModel::class.java)

        binding.arrowBack.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val adapter = HistoryAdapter()
        binding.rvHistory.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        binding.rvHistory.layoutManager = layoutManager

        historyViewModel.getAllHistoryData().observe(this) { result ->
            // Update the adapter with the new data
            adapter.submitList(result)
        }

    }
}