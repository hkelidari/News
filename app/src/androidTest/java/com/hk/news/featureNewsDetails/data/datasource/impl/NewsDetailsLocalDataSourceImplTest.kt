package com.hk.news.featureNewsDetails.data.datasource.impl

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.hk.news.core.data.db.NewsDatabase
import com.hk.news.core.data.db.newsEntity1
import com.hk.news.featureNewsDetails.data.NewsDetailsDao
import com.hk.news.featureNewsList.data.NewsListDao
import com.hk.news.featureNewsList.data.datasource.impl.NewsListLocalDataSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class NewsDetailsLocalDataSourceImplTest {

    private lateinit var database: NewsDatabase
    private lateinit var newsDetailsDao: NewsDetailsDao
    private lateinit var newsListDao: NewsListDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).build()
        newsDetailsDao = database.newsDetailsDao()
        newsListDao = database.newsListDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun canReadNewsDetailsFromTheDb() = runTest {

        // Arrange
        val newDetailsLocalDataSource = NewsDetailsLocalDataSourceImpl(newsDetailsDao)
        val newsListDataSource = NewsListLocalDataSourceImpl(newsListDao)
        newsListDataSource.saveTopHeadlines(listOf(newsEntity1))
        val expectedNewsDetails = newsEntity1

        // Act
        val actualNewsDetails = newDetailsLocalDataSource.getNewsDetails(1)


        // Assert
        assertEquals(expectedNewsDetails, actualNewsDetails)
    }


}