package com.example.unittesting.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TestModel::class], exportSchema = false, version = 1)
abstract class TestDB: RoomDatabase() {

    abstract fun testDao(): TestDao
}