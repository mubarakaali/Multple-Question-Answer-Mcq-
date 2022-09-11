package com.googleads

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.nativead.NativeAd
import com.googleads.ads_creation.AppOpenAdCreation
import com.googleads.ads_creation.InterstitialAdCreation
import com.googleads.ads_creation.NativeAdCreation
import com.googleads.listeners.SubscriptionListener
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
import javax.inject.Singleton

data class GoogleAdConfig(val debug: Boolean = false)

@Singleton
class AdsManagement @Inject constructor(
    @ApplicationContext private val context: Context,
    private val subscriptionListener: SubscriptionListener,
    private val config: GoogleAdConfig
) {

    private val testDeviceIds: List<String> = listOf(
        AdRequest.DEVICE_ID_EMULATOR,
        "B8152D3C1AC522BEE8729CB38F55367A")

    init {
        init()
    }

     fun init() {
//        if (subscriptionListener.getStatus(context)) {
//            return
//        }

        MobileAds.initialize(context)

        if (config.debug)
            MobileAds.setRequestConfiguration(
                RequestConfiguration.Builder()
                    .setTestDeviceIds(testDeviceIds)
                    .build()
            )

        InterstitialAdCreation.preload(context, subscriptionListener)
        NativeAdCreation.preload(context, subscriptionListener)
        AppOpenAdCreation.preload(context,subscriptionListener)
    }
    
    fun createInterstitialAd(configuration: (InterstitialAdCreation).() -> Unit) =
        InterstitialAdCreation(subscriptionListener).apply(configuration)

    fun createNativeAd(configuration: (NativeAdCreation).() -> Unit) =
        NativeAdCreation(subscriptionListener).also(configuration)

    fun createOpenAd(configuration:(AppOpenAdCreation).()->Unit) =
        AppOpenAdCreation(subscriptionListener).apply(configuration)

}