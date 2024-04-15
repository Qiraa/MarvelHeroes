package com.example.marvelsuperheroes.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SuperheroDao {

    @Query("select * from SuperheroEntity")
    suspend fun getAll(): List<SuperheroEntity>

    @Query("select * from SuperheroEntity where id = :id")
    suspend fun getById(id: String): SuperheroEntity?

    @Insert
    suspend fun insert(superheroes: List<SuperheroEntity>)

    @Insert
    suspend fun insert(superhero: SuperheroEntity)
}