package com.hk.news.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hk.news.featureNewsList.data.NewsListDao

@Database(entities = [NewsEntity::class], exportSchema = false, version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsListDao(): NewsListDao
}