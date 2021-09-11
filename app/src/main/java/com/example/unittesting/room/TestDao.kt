package com.example.unittesting.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.unittesting.resource.TestModel
import com.example.unittesting.resource.data.Article
import com.example.unittesting.resource.data.ArticleX
import retrofit2.Response

@Dao
interface TestDao {

    @Query("SELECT * from tbModel")
    fun getData(): LiveData<List<TestModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: TestModel)

    @Delete
    suspend fun deleteData(data: TestModel)

}