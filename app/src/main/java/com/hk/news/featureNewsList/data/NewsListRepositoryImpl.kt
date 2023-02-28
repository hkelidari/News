package com.hk.news.featureNewsList.data

import com.hk.news.core.data.api.Result
import com.hk.news.featureNewsList.data.api.toNewsEntity
import com.hk.news.featureNewsList.data.datasource.NewsListLocalDataSource
import com.hk.news.featureNewsList.data.datasource.NewsListRemoteDataSource
import com.hk.news.featureNewsList.domain.NewsListRepository
import com.hk.news.featureNewsList.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsListRepositoryImpl @Inject constructor(
    private val newsListLocalDataSource: NewsListLocalDataSource,
    private val newsListRemoteDataSource: NewsListRemoteDataSource
) : NewsListRepository {

    override fun getTopHeadlines(): Flow<List<News>> {

        return newsListLocalDataSource.getTopHeadlines().map { list -> list.map { it.toNews() } }

    }

    override suspend fun updateTopHeadlines(): Result<Unit> {
        return when (val result = newsListRemoteDataSource.getTopHeadlines()) {
            is Result.Success -> {

                newsListLocalDataSource.removeTopHeadlines()

                newsListLocalDataSource.saveTopHeadlines(result.data.articles.map {
                    it.toNewsEntity()
                })

                Result.Success(Unit)

            }
            is Result.Error -> {
                Result.Error(result.error)
            }

        }
    }
}