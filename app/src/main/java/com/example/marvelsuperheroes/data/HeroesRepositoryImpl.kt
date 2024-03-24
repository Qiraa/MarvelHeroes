package com.example.marvelsuperheroes.data

import com.example.marvelsuperheroes.data.api.CharactersResponse
import com.example.marvelsuperheroes.data.api.MarvelApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.math.BigInteger
import java.security.MessageDigest

private const val PUBLIC_API_KEY = "ffcbb0906e8b30f93686712b3a023956"
private const val PRIVATE_API_KEY = "e138cb0628f33887dc7c6968e4c08f8d4f2867e1"
private const val MILLIS_IN_SECOND = 1000L

class HeroesRepositoryImpl : HeroesRepository {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://gateway.marvel.com/v1/public/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val api: MarvelApi = retrofit.create()

    override suspend fun getAllHeroes(): List<Superhero> {
        val timestamp = getCurrentTimestamp()
        val charactersResponse = api.getSuperheroes(
            apiKey = PUBLIC_API_KEY,
            timeStamp = timestamp,
            hash = getHash(timestamp),
        )
        return mapToSuperheroes(charactersResponse)
    }

    override suspend fun getHeroById(id: String): Superhero {
        val timestamp = getCurrentTimestamp()
        val charactersResponse = api.getSuperhero(
            heroId = id,
            apiKey = PUBLIC_API_KEY,
            timeStamp = timestamp,
            hash = getHash(timestamp),
        )
        return mapToSuperheroes(charactersResponse)[0]
    }

    private fun mapToSuperheroes(charactersResponse: CharactersResponse): List<Superhero> {
        return charactersResponse.data.results.map { hero ->
            val imagePath = if (hero.image.path.startsWith("http://")) {
                hero.image.path.replace("http", "https")
            } else {
                hero.image.path
            }

            Superhero(
                id = hero.id,
                imageUrl = (imagePath + "." + hero.image.extension),
                name = hero.name,
                description = hero.description,
            )
        }
    }

    private fun getHash(timestamp: Long): String {
        return md5(timestamp.toString() + PRIVATE_API_KEY + PUBLIC_API_KEY)
    }

    private fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis() / MILLIS_IN_SECOND
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(
            1,
            md.digest(input.toByteArray())
        ).toString(16).padStart(32, '0')
    }
}
