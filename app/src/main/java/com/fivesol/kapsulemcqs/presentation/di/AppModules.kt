package com.fivesol.kapsulemcqs.presentation.di

import com.googleads.GoogleAdConfig
import com.googleads.listeners.SubscriptionListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Provides
    fun providesSubscriptionListeners():SubscriptionListener  = SubscriptionListener {
        false
    }
    @Provides
    fun providesGoogleAdsConfig(): GoogleAdConfig = GoogleAdConfig(debug = true)
}