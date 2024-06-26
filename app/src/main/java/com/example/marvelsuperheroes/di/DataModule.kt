package com.example.marvelsuperheroes.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.marvelsuperheroes.data.HeroesRepository
import com.example.marvelsuperheroes.data.HeroesRepositoryImpl
import com.example.marvelsuperheroes.data.api.MarvelApi
import com.example.marvelsuperheroes.data.db.AppDatabase
import com.example.marvelsuperheroes.data.db.SuperheroDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMarvelApi(retrofit: Retrofit): MarvelApi = retrofit.create()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "db")
            .build()
    }

    @Provides
    fun provideSuperheroDao(database: AppDatabase): SuperheroDao = database.getSuperheroesDao()

    @Singleton
    @Provides
    fun provideHeroesRepository(api: MarvelApi, dao: SuperheroDao): HeroesRepository =
        HeroesRepositoryImpl(api, dao)
}