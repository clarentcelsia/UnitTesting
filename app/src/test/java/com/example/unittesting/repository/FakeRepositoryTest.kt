package com.example.unittesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.unittesting.resource.TestModel
import com.example.unittesting.resource.data.Article
import com.example.unittesting.resource.data.ArticleX
import com.example.unittesting.util.Response

class FakeRepositoryTest: Repository {

    // This contains list of item
    private val itemList = mutableListOf<TestModel>()

    // This gets the news from list
    private val observableItem = MutableLiveData<List<TestModel>>(itemList)

    private var networkListenerSucceed = true

    fun listener(value: Boolean) {
        networkListenerSucceed = value
    }

    override fun getData(): LiveData<List<TestModel>> {
        return observableItem
    }

    override suspend fun deleteData(item: TestModel) {
        itemList.remove(item)
        updateData()
    }

    override suspend fun saveData(item: TestModel) {
        itemList.add(item)
        updateData()
    }

    override suspend fun searchNews(query: String): Response<Article> {
        return if(networkListenerSucceed) Response.success(Article(listOf(), "ok", 0))
        else Response.error("error", null)
    }

    private fun updateData(){
        observableItem.postValue(itemList)
    }

}
