package com.example.unittesting.repository

import androidx.lifecycle.LiveData
import com.example.unittesting.api.NewsAPI
import com.example.unittesting.resource.TestModel
import com.example.unittesting.resource.data.Article
import com.example.unittesting.resource.data.ArticleX
import com.example.unittesting.room.TestDao
import com.example.unittesting.util.Response
import javax.inject.Inject

class TestRepository @Inject constructor(
    private val dao: TestDao,
    private val api: NewsAPI,
) : Repository {

    override fun getData(): LiveData<List<TestModel>> {
        return dao.getData()
    }

    override suspend fun deleteData(item: TestModel) {
        dao.deleteData(item)
    }

    override suspend fun saveData(item: TestModel) {
        dao.insertData(item)
    }

    override suspend fun searchNews(query: String): Response<Article> {
        return try {
            val response = api.getSearchNews(query)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Response.success(it)
                }?: Response.error("error", null)
            }else{
                Response.error("an error occured", null)
            }
        }catch (e: Exception){
            Response.error(e.message!!, null)
        }
    }
}