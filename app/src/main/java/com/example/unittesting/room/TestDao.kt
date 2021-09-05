package com.example.unittesting.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TestDao {

    @Query("SELECT * from tbModel")
    fun getData(): LiveData<List<TestModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: TestModel)

    @Delete
    suspend fun deleteData(data: TestModel)

}