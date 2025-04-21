package com.example.atlystest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.atlystest.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}