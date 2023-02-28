package com.hk.news.featureNewsDetails.data.di

import com.hk.news.core.data.db.NewsDatabase
import com.hk.news.featureNewsDetails.data.NewsDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object NewsDetailsModule {

    @Provides
    fun provideNewsDetailsDao(newsDatabase: NewsDatabase): NewsDetailsDao {
        return newsDatabase.newsDetailsDao()
    }
}