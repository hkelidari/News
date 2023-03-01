package com.hk.news.featureNewsList.data

import com.hk.news.core.data.api.Exceptions
import com.hk.news.core.data.api.Result
import com.hk.news.featureNewsList.domain.NewsListRepository
import com.hk.news.featureNewsList.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeNewsListRepository(
    private val isSuccessful: Boolean = true
) : NewsListRepository {

    var newsList: Flow<List<News>> = flowOf()

    override fun getTopHeadlines(): Flow<List<News>> {
        return newsList
    }

    override suspend fun updateTopHeadlines(): Result<Unit> {
        return if (isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(Exceptions.GeneralRemoteException())
        }
    }
}