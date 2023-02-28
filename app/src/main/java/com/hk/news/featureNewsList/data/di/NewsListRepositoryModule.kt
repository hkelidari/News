package com.hk.news.featureNewsList.data.di

import com.hk.news.featureNewsList.data.NewsListRepositoryImpl
import com.hk.news.featureNewsList.data.datasource.NewsListLocalDataSource
import com.hk.news.featureNewsList.data.datasource.NewsListRemoteDataSource
import com.hk.news.featureNewsList.data.datasource.impl.NewsListLocalDataSourceImpl
import com.hk.news.featureNewsList.data.datasource.impl.NewsListRemoteDataSourceImpl
import com.hk.news.featureNewsList.domain.NewsListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Suppress("unused")
@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsListRepositoryModule {

    @Binds
    abstract fun bindNewsListLocalDataSource(impl: NewsListLocalDataSourceImpl): NewsListLocalDataSource

    @Binds
    abstract fun bindNewsListRemoteDataSource(impl: NewsListRemoteDataSourceImpl): NewsListRemoteDataSource

    @Binds
    abstract fun bindNewsListRepository(impl: NewsListRepositoryImpl): NewsListRepository

}