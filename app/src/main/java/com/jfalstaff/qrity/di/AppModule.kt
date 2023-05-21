package com.jfalstaff.qrity.di

import com.jfalstaff.qrity.data.RepositoryImpl
import com.jfalstaff.qrity.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(): Repository = RepositoryImpl()
}