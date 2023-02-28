package com.hk.news.featureNewsList.domain.usecase

import com.hk.news.core.data.api.Result
import com.hk.news.featureNewsList.domain.NewsListRepository
import javax.inject.Inject

class UpdateTopHeadlinesUseCase @Inject constructor(
    private val newsListRepository: NewsListRepository
) {

    suspend operator fun invoke(): Result<Unit> {
        return newsListRepository.updateTopHeadlines()
    }
}