package com.hk.news.featureNewsDetails.ui

import androidx.lifecycle.SavedStateHandle
import com.hk.news.featureNewsDetails.data.NewsDetailsDao
import com.hk.news.featureNewsDetails.data.NewsDetailsRepositoryImpl
import com.hk.news.featureNewsDetails.data.datasource.impl.NewsDetailsLocalDataSourceImpl
import com.hk.news.featureNewsDetails.domain.usecase.GetNewsDetailsUseCase
import com.hk.news.util.CoroutineRule
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsDetailsViewModelIntegrationTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val newsDetailsDao: NewsDetailsDao = mockk()

    private val localDataSource = NewsDetailsLocalDataSourceImpl(newsDetailsDao)

    private val repository = NewsDetailsRepositoryImpl(localDataSource)

    @Test
    fun `creating a viewModel triggers the dao get`() = runTest {

        // Arrange
        val saveState = SavedStateHandle(mapOf("id" to 1))

        NewsDetailsViewModel(
            GetNewsDetailsUseCase(repository),
            saveState
        )

        // Act
        runCurrent()

        // Assert
        coVerify(exactly = 1) { newsDetailsDao.getNewsDetails(1) }
    }
}