package com.hk.news.featureNewsList.data.datasource.impl

import com.hk.news.core.data.api.Exceptions
import com.hk.news.core.data.api.FakeConnectionManager
import com.hk.news.core.data.api.NetworkCallHandler
import com.hk.news.core.data.api.Result
import com.hk.news.featureNewsList.data.api.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
class NewsListRemoteDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: NewsListApiService

    private val client = OkHttpClient.Builder().build()

    @Before
    fun createServer() {
        mockWebServer = MockWebServer()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // setting a dummy url
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
            .create(NewsListApiService::class.java)
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `correct news list response is parsed into success result`() = runTest {

        // Arrange
        val response = MockResponse()
            .setBody(newsListSuccessfulResponse)
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val remoteDataSource = NewsListRemoteDataSourceImpl(
            apiService,
            NetworkCallHandler(FakeConnectionManager())
        )
        val expectedNewsList = listOf(newsResponse1, newsResponse2)

        // Act
        val result = remoteDataSource.getTopHeadlines()

        // Assert
        assert(result is Result.Success)
        assertEquals((result as Result.Success).data.articles, expectedNewsList)
    }

    @Test
    fun `malformed news list response returns error`() = runTest {

        // Arrange
        val response = MockResponse()
            .setBody(errorResponse)
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val remoteDataSource = NewsListRemoteDataSourceImpl(
            apiService,
            NetworkCallHandler(FakeConnectionManager())
        )

        // Act
        val result = remoteDataSource.getTopHeadlines()

        // Assert
        assert(result is Result.Error)
        assert((result as Result.Error).error is Exceptions.GeneralRemoteException)
    }

    @Test
    fun `error news list response returns error result`() = runTest {

        // Arrange
        val response = MockResponse()
            .setBody(newsListSuccessfulResponse)
            .setResponseCode(400)

        mockWebServer.enqueue(response)

        val remoteDataSource = NewsListRemoteDataSourceImpl(
            apiService,
            NetworkCallHandler(FakeConnectionManager())
        )

        // Act
        val result = remoteDataSource.getTopHeadlines()

        // Assert
        assert(result is Result.Error)
        assert((result as Result.Error).error is Exceptions.GeneralRemoteException)
    }

}