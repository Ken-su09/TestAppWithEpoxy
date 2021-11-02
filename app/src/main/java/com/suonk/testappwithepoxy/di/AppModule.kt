package com.suonk.testappwithepoxy.di

import com.suonk.testappwithepoxy.repositories.AttractionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideRepository() = AttractionsRepository()
}