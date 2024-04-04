package com.dicoding.capstone.ui.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.core.data.Resource
import com.dicoding.capstone.core.ui.GameAdapter
import com.dicoding.capstone.databinding.ActivityMainBinding
import com.dicoding.capstone.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvGame.layoutManager = layoutManager

        val adapter = GameAdapter()
        adapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        binding.btnFavorite.setOnClickListener {
            val uri = Uri.parse("capstone://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        mainViewModel.game.observe(this) { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.setData(game.data)
                        binding.rvGame.adapter = adapter
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
//                        TODO("1. bikin error handling")
                    }
                }
            }
        }
    }
}