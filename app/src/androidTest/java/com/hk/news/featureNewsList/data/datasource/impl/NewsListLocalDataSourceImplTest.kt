package com.hk.news.featureNewsList.data.datasource.impl

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.hk.news.core.data.db.NewsDatabase
import com.hk.news.core.data.db.newsEntity1
import com.hk.news.core.data.db.newsEntity2
import com.hk.news.featureNewsList.data.NewsListDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class NewsListLocalDataSourceImplTest {

    private lateinit var database: NewsDatabase
    private lateinit var newsListDao: NewsListDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).build()
        newsListDao = database.newsListDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun canSaveNewsListToTheDbAndObserveData() = runTest {

        // Arrange
        val localDataSource = NewsListLocalDataSourceImpl(newsListDao)
        val expectedList = listOf(newsEntity1, newsEntity2)

        // Act
        val newsListFlow = localDataSource.getTopHeadlines()
        localDataSource.saveTopHeadlines(listOf(newsEntity1, newsEntity2))

        // Assert
        newsListFlow.test {
            assertEquals(expectedList, awaitItem())
        }
    }

    @Test
    fun canRemoveNewsListFromTheDb() = runTest {

        // Arrange
        val localDataSource = NewsListLocalDataSourceImpl(newsListDao)
        localDataSource.saveTopHeadlines(listOf(newsEntity1, newsEntity2))

        // Act
        val newsFlow = localDataSource.getTopHeadlines()
        localDataSource.removeTopHeadlines()


        // Assert
        newsFlow.test {
            assertEquals(0, awaitItem().size)
        }
    }
}