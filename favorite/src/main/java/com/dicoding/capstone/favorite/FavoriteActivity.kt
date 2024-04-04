package com.dicoding.capstone.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.core.ui.GameAdapter
import com.dicoding.capstone.databinding.ActivityFavoriteBinding
import com.dicoding.capstone.favorite.di.favoriteModule
import com.dicoding.capstone.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val layoutManager = LinearLayoutManager(this)
        binding.rvGame.layoutManager = layoutManager

        val adapter = GameAdapter()
        adapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        binding.tvNoFavorite.visibility = View.GONE

        viewModel.favoriteGame.observe(this) { game ->
            if (game != null) {
                Log.i("FavoriteActivity", game.size.toString())
                binding.progressBar.visibility = View.GONE
                adapter.setData(game)
                binding.rvGame.adapter = adapter
            }
            if (game.isEmpty()) {
                binding.tvNoFavorite.visibility = View.VISIBLE
            }
        }
    }
}