package com.example.unittesting.repository

import androidx.lifecycle.LiveData
import com.example.unittesting.resource.TestModel
import com.example.unittesting.resource.data.Article
import com.example.unittesting.resource.data.ArticleX
import com.example.unittesting.util.Response

interface Repository {

    fun getData(): LiveData<List<TestModel>>

    suspend fun deleteData(item: TestModel)

    suspend fun saveData(item: TestModel)


    suspend fun searchNews(query: String): Response<Article>
}