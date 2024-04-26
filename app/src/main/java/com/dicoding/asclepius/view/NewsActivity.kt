package com.dicoding.asclepius.view

import NewsAdapter
import NewsViewModel
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.data.response.ArticlesItem
import com.dicoding.asclepius.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity(){

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.arrowBack.setOnClickListener {
            // Navigate back to the main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: finish this activity to remove it from the back stack
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager

        val newsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[NewsViewModel::class.java]

        newsViewModel.listNews.observe(this) { news ->
            Log.d(TAG, "Observed news data: $news")
            setNewsData(news)
        }
    }

    private fun setNewsData(listNews: List<ArticlesItem>) {
        val adapter = NewsAdapter()
        binding.rvNews.adapter = adapter // Atur adapter RecyclerView di sini
        adapter.submitList(listNews) // Meneruskan data ke adapter setelah adapter diatur


    }


}