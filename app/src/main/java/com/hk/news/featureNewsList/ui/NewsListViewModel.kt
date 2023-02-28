package com.hk.news.featureNewsList.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hk.news.core.data.api.Result
import com.hk.news.core.ui.collectOn
import com.hk.news.featureNewsList.domain.usecase.GetTopHeadlinesUseCase
import com.hk.news.featureNewsList.domain.usecase.UpdateTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val updateTopHeadlinesUseCase: UpdateTopHeadlinesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsListUiState())
    val uiState: StateFlow<NewsListUiState> = _uiState.asStateFlow()


    init {
        getTopHeadlines()
        updateTopHeadLines()
    }

    fun getTopHeadlines() {

        getTopHeadlinesUseCase().collectOn(viewModelScope) { newsList ->
            _uiState.update { it.copy(newsList = newsList) }
        }
    }

    fun updateTopHeadLines() {

        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            when (val result = updateTopHeadlinesUseCase()) {
                is Result.Success -> {
                    _uiState.update { it.copy(isLoading = false, error = null) }
                }
                is Result.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.error) }
                }
            }
        }
    }

}