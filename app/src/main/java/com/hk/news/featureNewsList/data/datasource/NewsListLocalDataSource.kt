package com.hk.news.featureNewsList.data.datasource

import com.hk.news.core.data.db.NewsEntity
import kotlinx.coroutines.flow.Flow

interface NewsListLocalDataSource {

    fun getTopHeadlines(): Flow<List<NewsEntity>>

    suspend fun saveTopHeadlines(news: List<NewsEntity>)

    suspend fun removeTopHeadlines()
}