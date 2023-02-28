package com.hk.news.featureNewsDetails.data.datasource

import com.hk.news.core.data.db.NewsEntity

interface NewsDetailsLocalDataSource {

    suspend fun getNewsDetails(id: Int): NewsEntity

}