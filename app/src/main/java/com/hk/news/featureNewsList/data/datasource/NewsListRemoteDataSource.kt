package com.hk.news.featureNewsList.data.datasource

import com.hk.news.core.data.api.Result
import com.hk.news.featureNewsList.data.api.NewsListResponse

interface NewsListRemoteDataSource {

    suspend fun getTopHeadlines(): Result<NewsListResponse>
}