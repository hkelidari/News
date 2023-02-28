package com.hk.news.featureNewsDetails.data.di

import com.hk.news.featureNewsDetails.data.NewsDetailsRepositoryImpl
import com.hk.news.featureNewsDetails.data.datasource.NewsDetailsLocalDataSource
import com.hk.news.featureNewsDetails.data.datasource.impl.NewsDetailsLocalDataSourceImpl
import com.hk.news.featureNewsDetails.domain.NewsDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Suppress("unused")
@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsDetailsRepositoryModule {

    @Binds
    abstract fun bindNewsDetailsLocalDataSource(impl: NewsDetailsLocalDataSourceImpl): NewsDetailsLocalDataSource

    @Binds
    abstract fun bindNewsDetailsRepository(impl: NewsDetailsRepositoryImpl): NewsDetailsRepository

}