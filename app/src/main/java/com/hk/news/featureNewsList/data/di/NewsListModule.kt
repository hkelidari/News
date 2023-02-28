package com.hk.news.featureNewsList.data.di

import com.hk.news.core.data.db.NewsDatabase
import com.hk.news.featureNewsList.data.NewsListDao
import com.hk.news.featureNewsList.data.api.NewsListApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object NewsListModule {

    @Provides
    fun provideNewsListDao(newsDatabase: NewsDatabase): NewsListDao {
        return newsDatabase.newsListDao()
    }

    @Provides
    fun provideNewsListApiService(retrofit: Retrofit): NewsListApiService {
        return retrofit.create(NewsListApiService::class.java)
    }
}