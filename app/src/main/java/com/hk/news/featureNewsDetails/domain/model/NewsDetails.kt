package com.hk.news.featureNewsDetails.domain.model

data class NewsDetails(
    val id: Int,
    val title: String,
    val description: String,
    val content: String,
    val urlToImage: String
)