package com.hk.news.featureNewsDetails.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hk.news.featureNewsDetails.domain.usecase.GetNewsDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val getNewsDetailsUseCase: GetNewsDetailsUseCase,
    state: SavedStateHandle
) : ViewModel() {

    private val newsId = NewsDetailsFragmentArgs.fromSavedStateHandle(state).id

    private val _uiState = MutableStateFlow(NewsDetailsUiState())
    val uiState: StateFlow<NewsDetailsUiState> = _uiState.asStateFlow()

    init {
        getNewsDetails()
    }

    private fun getNewsDetails() {

        viewModelScope.launch {
            val result = getNewsDetailsUseCase(newsId)
            _uiState.update { it.copy(newsDetails = result) }
        }
    }

}