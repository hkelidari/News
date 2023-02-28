package com.hk.news.featureNewsList.data.datasource.impl

import com.hk.news.core.data.db.NewsEntity
import com.hk.news.featureNewsList.data.NewsListDao
import com.hk.news.featureNewsList.data.datasource.NewsListLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsListLocalDataSourceImpl @Inject constructor(
    private val newsListDao: NewsListDao
) : NewsListLocalDataSource {

    override fun getTopHeadlines(): Flow<List<NewsEntity>> {
        return newsListDao.getTopHeadlines()
    }

    override suspend fun saveTopHeadlines(news: List<NewsEntity>) {
        newsListDao.insertTopHeadlines(news)
    }

    override suspend fun removeTopHeadlines() {
        newsListDao.deleteTopHeadlines()
    }
}