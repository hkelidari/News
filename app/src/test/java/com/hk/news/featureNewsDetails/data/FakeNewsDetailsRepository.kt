package com.hk.news.featureNewsDetails.data

import com.hk.news.featureNewsDetails.domain.NewsDetailsRepository
import com.hk.news.featureNewsDetails.domain.model.NewsDetails

class FakeNewsDetailsRepository : NewsDetailsRepository {

    var newsDetails: NewsDetails? = null
    override suspend fun getNewsDetails(id: Int): NewsDetails {
        return newsDetails ?: NewsDetails(0, "", "", "", "")
    }
}