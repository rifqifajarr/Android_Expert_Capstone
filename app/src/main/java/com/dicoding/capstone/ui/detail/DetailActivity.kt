package com.dicoding.capstone.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.capstone.R
import com.dicoding.capstone.core.domain.model.Game
import com.dicoding.capstone.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private var binding: ActivityDetailBinding? = null

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val detailGame = intent.getParcelableExtra<Game>(EXTRA_DATA)
        if (detailGame != null) {
            showDetailGame(detailGame)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun showDetailGame(detailGame: Game) {
        binding?.let {
            it.tvTitle.text = detailGame.name
            it.tvRating.text = getString(R.string.rating, detailGame.rating)
            it.tvMetacritic.text = getString(R.string.metacritic, detailGame.metaCritic)
            it.tvReleased.text = getString(R.string.released, detailGame.released)
            it.tvPlaytime.text = getString(R.string.play_time_hours, detailGame.playTime)
        }

        binding?.ivGameImage?.let {
            Glide.with(this)
                .load(detailGame.imageUrl)
                .into(it)
        }

        var favoriteStatus = detailGame.isFavorite
        setFavoriteStatus(favoriteStatus)
        binding?.fabFavorite?.setOnClickListener {
            favoriteStatus = !favoriteStatus
            viewModel.setFavoriteGame(detailGame, favoriteStatus)
            setFavoriteStatus(favoriteStatus)
        }
    }

    private fun setFavoriteStatus(favorite: Boolean) {
        if (favorite) {
            binding?.fabFavorite?.setImageDrawable(ContextCompat.getDrawable(this,
                R.drawable.baseline_favorite_24
            ))
        } else {
            binding?.fabFavorite?.setImageDrawable(ContextCompat.getDrawable(this,
                R.drawable.baseline_favorite_border_24
            ))
        }
    }
}