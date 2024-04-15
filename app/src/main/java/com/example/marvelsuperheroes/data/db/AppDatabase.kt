package com.example.marvelsuperheroes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SuperheroEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSuperheroesDao(): SuperheroDao
}