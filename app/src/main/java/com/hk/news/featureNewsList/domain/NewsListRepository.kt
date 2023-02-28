package com.hk.news.featureNewsList.domain

import com.hk.news.core.data.api.Result
import com.hk.news.featureNewsList.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsListRepository {

    fun getTopHeadlines(): Flow<List<News>>

    suspend fun updateTopHeadlines(): Result<Unit>
}