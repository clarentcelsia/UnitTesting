package com.example.unittesting

import android.content.Context
import androidx.room.Room
import com.example.unittesting.api.NewsAPI
import com.example.unittesting.repository.Repository
import com.example.unittesting.repository.TestRepository
import com.example.unittesting.room.TestDB
import com.example.unittesting.room.TestDao
import com.example.unittesting.util.Constant.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, TestDB::class.java, "dbTest")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun dao(db: TestDB) = db.testDao()

    @Provides
    @Singleton
    fun provideRetrofitandAPi() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsAPI::class.java)

    @Provides
    @Singleton
    fun provideRepository(
        dao: TestDao,
        api: NewsAPI
    ) = TestRepository(dao, api) as Repository
}