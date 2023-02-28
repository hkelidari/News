package com.hk.news.featureNewsList.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hk.news.core.data.db.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsListDao {

    @Query("SELECT * FROM NewsEntity ORDER BY publishedAt Desc")
    fun getTopHeadlines(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopHeadlines(news: List<NewsEntity>)

    @Query("DELETE FROM NewsEntity")
    suspend fun deleteTopHeadlines()
}