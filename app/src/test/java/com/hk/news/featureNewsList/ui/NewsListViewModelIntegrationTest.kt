package com.hk.news.featureNewsList.ui

import com.hk.news.core.data.api.ConnectionManager
import com.hk.news.core.data.api.FakeConnectionManager
import com.hk.news.core.data.api.NetworkCallHandler
import com.hk.news.featureNewsList.data.NewsListDao
import com.hk.news.featureNewsList.data.NewsListRepositoryImpl
import com.hk.news.featureNewsList.data.api.NewsListApiService
import com.hk.news.featureNewsList.data.datasource.impl.NewsListLocalDataSourceImpl
import com.hk.news.featureNewsList.data.datasource.impl.NewsListRemoteDataSourceImpl
import com.hk.news.featureNewsList.domain.usecase.GetTopHeadlinesUseCase
import com.hk.news.featureNewsList.domain.usecase.UpdateTopHeadlinesUseCase
import com.hk.news.util.CoroutineRule
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsListViewModelIntegrationTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val mockNewsListApiService: NewsListApiService = mockk(relaxed = true)
    private val newsListDao: NewsListDao = mockk(relaxed = true)
    private val fakeConnectionManager: ConnectionManager = FakeConnectionManager()

    private val remoteDataSource = NewsListRemoteDataSourceImpl(
        mockNewsListApiService,
        NetworkCallHandler(fakeConnectionManager)
    )
    private val localDataSource = NewsListLocalDataSourceImpl(newsListDao)

    private val repository = NewsListRepositoryImpl(
        newsListLocalDataSource = localDataSource,
        newsListRemoteDataSource = remoteDataSource
    )

    @Test
    fun `creating a viewModel triggers the api service`() = runTest {

        // Arrange
        NewsListViewModel(
            updateTopHeadlinesUseCase = UpdateTopHeadlinesUseCase(repository),
            getTopHeadlinesUseCase = GetTopHeadlinesUseCase(repository)
        )


        // Act
        runCurrent()

        // Assert
        coVerify(exactly = 1) { mockNewsListApiService.getTopHeadlines() }
    }

    @Test
    fun `creating a viewModel triggers the dao get`() = runTest {

        // Arrange
        NewsListViewModel(
            updateTopHeadlinesUseCase = UpdateTopHeadlinesUseCase(repository),
            getTopHeadlinesUseCase = GetTopHeadlinesUseCase(repository)
        )

        // Assert
        verify(exactly = 1) { newsListDao.getTopHeadlines() }
    }
}