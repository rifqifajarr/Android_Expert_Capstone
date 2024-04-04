package com.dicoding.capstone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val gameId: String,
    val name: String,
    val rating: String,
    val metaCritic: String,
    val released: String,
    val playTime: String,
    val imageUrl: String,
    val isFavorite: Boolean
) : Parcelable
