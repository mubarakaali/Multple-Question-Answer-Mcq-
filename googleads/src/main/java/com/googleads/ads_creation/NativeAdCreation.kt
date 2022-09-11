package com.googleads.ads_creation

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.VideoOptions
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.googleads.ads_type.NativeAdsType
import com.googleads.error.AdErrorCode
import com.googleads.error.toLoadAdError
import com.googleads.listeners.SubscriptionListener

class NativeAdCreation(private val subscription: SubscriptionListener) : LifecycleObserver {

    companion object {
        private val cache: MutableMap<NativeAdsType, NativeAdCreation?> = mutableMapOf()

        private val videoOptions: VideoOptions by lazy {
            VideoOptions.Builder()
                .setStartMuted(true)
                .build()
        }

        private val defaultNativeAdOption: NativeAdOptions by lazy {
            NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build()
        }

        private val defaultAdListener: AdListener by lazy {
            object : AdListener() {}
        }

        fun preload(context: Context, subscription: SubscriptionListener) {
            preload(context, NativeAdsType.values().toList(), subscription)
        }

        private fun preload(
            context: Context,
            reloadVariants: Collection<NativeAdsType>,
            subscription: SubscriptionListener
        ) {
            reloadVariants.forEach {
                NativeAdCreation(subscription).loadCache(context, it)
            }
        }
    }

    private var lifecycle: Lifecycle? = null
    var nativeAd: NativeAd? = null

    var adListener: AdListener = defaultAdListener
    var nativeAdOptions: NativeAdOptions = defaultNativeAdOption

    var variant: NativeAdsType = NativeAdsType.DEFAULT
    private val selectedVariant: String
        get() = variant.id

    var onAdAvailable: ((NativeAd) -> Unit)? = null

    fun showCached(context: Context, lifecycle: Lifecycle?) {
//        if (subscription.getStatus(context)) {
//            adListener.onAdClosed()
//            return
//        }

        cache[variant]?.let { cached ->
            cached.lifecycle = lifecycle?.apply { addObserver(cached) }
            cached.nativeAd?.let {
                onAdAvailable?.invoke(it)
            } ?: adListener.onAdFailedToLoad(AdErrorCode.CACHE_NULL.toLoadAdError())
        } ?: adListener.onAdFailedToLoad(AdErrorCode.CACHE_NULL.toLoadAdError())

        cache[variant] = null
        preload(context, listOf(variant), subscription)
    }

    fun show(context: Context, lifecycle: Lifecycle?) {
//        if (subscription.getStatus(context)) {
//            adListener.onAdClosed()
//            return
//        }

        this.lifecycle = lifecycle
        lifecycle?.addObserver(this)

        AdLoader.Builder(context, selectedVariant)
            .forNativeAd {
                nativeAd = it
                onAdAvailable?.invoke(it)
            }
            .withAdListener(adListener)
            .withNativeAdOptions(nativeAdOptions)
            .build()
            .loadAd(AdRequest.Builder().build())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun disconnectListener() {
        lifecycle?.removeObserver(this)
        nativeAd?.destroy()
        nativeAd = null
    }

    private fun loadCache(context: Context, variant: NativeAdsType) {
        AdLoader.Builder(context, variant.id)
            .forNativeAd {
                nativeAd = it
                cache[variant] = this
            }
            .withAdListener(adListener)
            .withNativeAdOptions(nativeAdOptions)
            .build()
            .loadAd(AdRequest.Builder().build())
    }

}