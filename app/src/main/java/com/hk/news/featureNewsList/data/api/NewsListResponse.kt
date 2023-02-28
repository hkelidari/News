package com.hk.news.featureNewsList.data.api

import com.hk.news.core.data.db.NewsEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsListResponse(
    val status: String,
    val code: String?,
    val message: String?,
    val totalResults: Int,
    val articles: List<Article>
)

@JsonClass(generateAdapter = true)
data class Article(
    val title: String,
    val description: String,
    val content: String,
    val publishedAt: String,
    val urlToImage: String
)

fun Article.toNewsEntity() = NewsEntity(
    title = title,
    description = description,
    content = content,
    publishedAt = publishedAt,
    urlToImage = urlToImage

)