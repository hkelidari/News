package com.hk.news.featureNewsDetails.domain.usecase

import com.hk.news.featureNewsDetails.domain.NewsDetailsRepository
import com.hk.news.featureNewsDetails.domain.model.NewsDetails
import javax.inject.Inject

class GetNewsDetailsUseCase @Inject constructor(
    private val newsDetailsRepository: NewsDetailsRepository
) {

    suspend operator fun invoke(id: Int): NewsDetails {
        return newsDetailsRepository.getNewsDetails(id)
    }
}