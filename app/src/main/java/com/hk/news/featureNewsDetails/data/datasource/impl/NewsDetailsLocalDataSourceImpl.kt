package com.hk.news.featureNewsDetails.data.datasource.impl

import com.hk.news.core.data.db.NewsEntity
import com.hk.news.featureNewsDetails.data.NewsDetailsDao
import com.hk.news.featureNewsDetails.data.datasource.NewsDetailsLocalDataSource
import javax.inject.Inject

class NewsDetailsLocalDataSourceImpl @Inject constructor(
    private val newsDetailsDao: NewsDetailsDao
) : NewsDetailsLocalDataSource {

    override suspend fun getNewsDetails(id: Int): NewsEntity {
        return newsDetailsDao.getNewsDetails(id)
    }
}