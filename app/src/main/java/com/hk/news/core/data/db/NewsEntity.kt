package com.hk.news.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hk.news.featureNewsList.domain.model.News

@Entity
data class NewsEntity(
    @PrimaryKey(true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val content: String,
    val publishedAt: String,
    val urlToImage: String
) {

    fun toNews() = News(
        id = id,
        title = title,
        urlToImage = urlToImage
    )
}
