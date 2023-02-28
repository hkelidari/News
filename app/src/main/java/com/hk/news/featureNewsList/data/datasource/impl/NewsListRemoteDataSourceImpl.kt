package com.hk.news.featureNewsList.data.datasource.impl

import com.hk.news.core.data.api.NetworkCallHandler
import com.hk.news.core.data.api.Result
import com.hk.news.featureNewsList.data.api.NewsListApiService
import com.hk.news.featureNewsList.data.api.NewsListResponse
import com.hk.news.featureNewsList.data.datasource.NewsListRemoteDataSource
import javax.inject.Inject

class NewsListRemoteDataSourceImpl @Inject constructor(
    private val newsListApiService: NewsListApiService,
    private val networkCallHandler: NetworkCallHandler
) : NewsListRemoteDataSource {

    override suspend fun getTopHeadlines(): Result<NewsListResponse> {

        return networkCallHandler.call {
            newsListApiService.getTopHeadlines()
        }

    }
}