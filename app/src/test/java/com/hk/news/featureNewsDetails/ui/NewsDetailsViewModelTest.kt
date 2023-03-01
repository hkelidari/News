package com.hk.news.featureNewsDetails.ui

import androidx.lifecycle.SavedStateHandle
import com.hk.news.featureNewsDetails.data.FakeNewsDetailsRepository
import com.hk.news.featureNewsDetails.domain.model.newsDetails
import com.hk.news.featureNewsDetails.domain.usecase.GetNewsDetailsUseCase
import com.hk.news.util.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsDetailsViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Test
    fun `creating a viewModel retrieves news details from local`() = runTest {

        // Arrange
        val fakeRepository = FakeNewsDetailsRepository()
        fakeRepository.newsDetails = newsDetails

        val saveState = SavedStateHandle(mapOf("id" to 1))

        val viewModel = NewsDetailsViewModel(
            GetNewsDetailsUseCase(fakeRepository),
            saveState
        )

        val expectedState = NewsDetailsUiState(newsDetails = newsDetails)

        // Act
        runCurrent()

        // Assert
        val actualState = viewModel.uiState.value
        assertEquals(expectedState, actualState)

    }
}