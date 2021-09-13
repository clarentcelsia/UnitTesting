package com.example.unittesting

import android.content.Context
import androidx.room.Room
import com.example.unittesting.room.TestDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    // In this test case, I don't want this db to be a singleton
    // because I want to create a new instance of it for every single test case.

    // inMemoryDatabase is not a persistent storage database but
    // instead just a database in the RAM basically.
    @Provides
    @Named("testDB")
    fun provideInMemoryDB(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, TestDB::class.java)
            .allowMainThreadQueries()
            .build()
}