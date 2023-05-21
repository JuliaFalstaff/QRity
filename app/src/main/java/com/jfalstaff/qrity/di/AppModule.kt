package com.jfalstaff.qrity.di

import com.jfalstaff.qrity.data.RepositoryImpl
import com.jfalstaff.qrity.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun provideRepository(impl: RepositoryImpl): Repository
}