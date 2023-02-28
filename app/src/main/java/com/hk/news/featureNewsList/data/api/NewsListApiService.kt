package com.hk.news.featureNewsList.data.api

import com.hk.news.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsListApiService {

    @Headers("X-Api-Key: ${BuildConfig.API_KEY}")
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") source: String = "bbc-news",
    ): Response<NewsListResponse>
}