package com.hk.news.featureNewsList.domain.usecase

import com.hk.news.featureNewsList.domain.NewsListRepository
import com.hk.news.featureNewsList.domain.model.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val newsListRepository: NewsListRepository
) {

    operator fun invoke(): Flow<List<News>> {
        return newsListRepository.getTopHeadlines()
    }
}