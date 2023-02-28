package com.hk.news.featureNewsList.ui

import com.hk.news.core.data.api.Exceptions
import com.hk.news.featureNewsList.domain.model.News

data class NewsListUiState(
    val newsList: List<News> = emptyList(),
    val error: Exceptions? = null,
    val isLoading: Boolean = false
)