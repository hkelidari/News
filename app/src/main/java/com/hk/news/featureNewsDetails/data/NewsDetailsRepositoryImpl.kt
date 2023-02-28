package com.hk.news.featureNewsDetails.data

import com.hk.news.featureNewsDetails.data.datasource.NewsDetailsLocalDataSource
import com.hk.news.featureNewsDetails.domain.NewsDetailsRepository
import com.hk.news.featureNewsDetails.domain.model.NewsDetails
import javax.inject.Inject

class NewsDetailsRepositoryImpl @Inject constructor(
    private val newsDetailsLocalDataSource: NewsDetailsLocalDataSource
) : NewsDetailsRepository {

    override suspend fun getNewsDetails(id: Int): NewsDetails {
        return newsDetailsLocalDataSource.getNewsDetails(id).toNewsDetails()
    }
}