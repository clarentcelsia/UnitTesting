package com.example.unittesting.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unittesting.repository.Repository
import com.example.unittesting.resource.TestModel
import com.example.unittesting.resource.data.Article
import com.example.unittesting.util.Event
import com.example.unittesting.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    private var _searchNews = MutableLiveData<Event<Response<Article>>>()
    val searchNews: LiveData<Event<Response<Article>>> = _searchNews

    private var _status = MutableLiveData<Event<Response<TestModel>>>()
    val status: LiveData<Event<Response<TestModel>>> = _status

    fun getData() = viewModelScope.launch {
        repo.getData()
    }

    fun deleteItem(item: TestModel) = viewModelScope.launch {
        repo.deleteData(item)
    }

    fun insertItem(item: TestModel) = viewModelScope.launch {
        repo.saveData(item)
    }

    fun validation(name: String, stock: Int, price: Double) {
        if(name.isEmpty()){
            // this sets a value from a background thread and send it to main thread
            _status.postValue(Event(Response.error("Field musnt be empty", null)))
            return
        }
        val item = TestModel(name, stock, price)
        insertItem(item)
        _status.postValue(Event(Response.success(item)))
    }


    fun searchNews(query: String) {
        if (query.isNotEmpty()) {
            // this .value, the change will notify all the observers of that live data
                // if using postValue() for searching [short time frame], the change only observe
                    // the last passed. In this test case, I want the observer get the response loading first
                        // not directly the success response to know if the test works properly.
            _searchNews.value = Event(Response.loading(null))
            viewModelScope.launch {
                val response = repo.searchNews(query)
                _searchNews.value = Event(response)
            }
        }
    }
}