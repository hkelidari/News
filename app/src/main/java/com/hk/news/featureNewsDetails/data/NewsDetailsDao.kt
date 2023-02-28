package com.hk.news.featureNewsDetails.data

import androidx.room.Dao
import androidx.room.Query
import com.hk.news.core.data.db.NewsEntity

@Dao
interface NewsDetailsDao {

    @Query("SELECT * FROM NewsEntity WHERE id = :id")
    suspend fun getNewsDetails(id: Int): NewsEntity
}