package com.monosoft.myprofile.di

import android.content.Context
import com.monosoft.myprofile.data.repository.NetworkCheckedImpl
import com.monosoft.myprofile.domain.repository.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkCheckedModule {
    @Provides
    @Singleton
    fun provideNetworkChecker(@ApplicationContext context: Context): NetworkChecker {
        return NetworkCheckedImpl(context)
    }
}