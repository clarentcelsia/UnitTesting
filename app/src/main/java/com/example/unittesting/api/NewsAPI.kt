package com.example.unittesting.api

import com.example.unittesting.BuildConfig
import com.example.unittesting.resource.data.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("everything")
    suspend fun getSearchNews(
        @Query("q") query: String = "",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = BuildConfig.key
    ): Response<Article>

}