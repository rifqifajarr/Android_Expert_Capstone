package com.dicoding.capstone.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    @ColumnInfo(name = "gameId")
    val gameId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "rating")
    val rating: String,

    @ColumnInfo(name = "metacritic")
    val metacritic: String,

    @ColumnInfo(name = "released")
    val released: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,

    @ColumnInfo(name = "playTime")
    val playTime: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
