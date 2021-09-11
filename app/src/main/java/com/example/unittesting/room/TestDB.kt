package com.example.unittesting.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.unittesting.resource.TestModel
import com.example.unittesting.resource.converter.SourceConverter
import com.example.unittesting.resource.data.ArticleX

@Database(entities = [TestModel::class], exportSchema = false, version = 1)
@TypeConverters(SourceConverter::class)
abstract class TestDB: RoomDatabase() {

    abstract fun testDao(): TestDao
}