package com.hk.news.featureNewsDetails.domain

import com.hk.news.featureNewsDetails.domain.model.NewsDetails

interface NewsDetailsRepository {

    suspend fun getNewsDetails(id: Int): NewsDetails
}