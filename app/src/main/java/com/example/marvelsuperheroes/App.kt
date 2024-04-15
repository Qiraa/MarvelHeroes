package com.example.marvelsuperheroes

import android.app.Application
import androidx.room.Room
import com.example.marvelsuperheroes.data.db.AppDatabase

class App : Application() {

    companion object {

        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, "db")
            .build()
    }
}