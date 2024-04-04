package com.dicoding.capstone.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.capstone.core.data.source.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 3, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
}