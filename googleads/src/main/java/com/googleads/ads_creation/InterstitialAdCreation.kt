package com.googleads.ads_creation

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.googleads.ads_type.InterstitialTypeAds
import com.googleads.listeners.SubscriptionListener

private const val TAG = "InterstitialAdCreation"

class InterstitialAdCreation(private val subscription: SubscriptionListener) {

    companion object {
        private val cache: MutableMap<InterstitialTypeAds, InterstitialAdCreation?> =
            mutableMapOf()

        fun preload(context: Context, subscription: SubscriptionListener) {
            preload(context, InterstitialTypeAds.values().toList(), subscription)
        }

        private fun preload(
            context: Context,
            reloadVariants: Collection<InterstitialTypeAds>,
            subscription: SubscriptionListener
        ) {
            reloadVariants.forEach {
                InterstitialAdCreation(subscription).loadCache(context, it)
            }
        }
    }

    var type: InterstitialTypeAds = InterstitialTypeAds.DEFAULT_SELECTION
    var onAdFailed: (() -> Unit)? = null
    var onAdLoaded: (() -> Unit)? = null
    var onAdDismiss: (() -> Unit)? = null

    val interstitialId: String
        get() = type.id

    val fullScreenCallback
        get() = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                onAdDismiss?.invoke()
            }

            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                super.onAdFailedToShowFullScreenContent(error)
                onAdFailed?.invoke() ?: onAdDismiss?.invoke()
            }
        }

    private var interstitialAd: InterstitialAd? = null

    fun showCached(activity: Activity) {
//        if (subscription.getStatus(activity)) {
//            onAdDismiss?.invoke()
//            return
//        }

        cache[type]?.let { cachedAd ->
            cachedAd.type = type
            cachedAd.onAdDismiss = onAdDismiss
            cachedAd.onAdLoaded = onAdLoaded
            cachedAd.onAdFailed = onAdFailed

            cachedAd.interstitialAd?.show(activity) ?: onAdDismiss?.invoke()
        } ?: onAdFailed?.invoke() ?: onAdDismiss?.invoke()

        cache[type] = null
        preload(activity, listOf(type), subscription)
    }


    private fun getAdCallback(
        onAdLoaded: ((InterstitialAd?) -> Unit)?,
        onAdFailed: (LoadAdError) -> Unit
    ): InterstitialAdLoadCallback {
        return object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                interstitialAd.fullScreenContentCallback = fullScreenCallback
                super.onAdLoaded(interstitialAd)
                onAdLoaded?.invoke(interstitialAd)
            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                super.onAdFailedToLoad(error)
                Log.w(TAG, "onAdFailedToLoad: $error")
                onAdFailed.invoke(error)
            }
        }
    }

    private fun loadCache(context: Context, variant: InterstitialTypeAds) {
        val request = AdRequest.Builder().build()
        InterstitialAd.load(context, variant.id, request, getAdCallback({
            interstitialAd = it
            cache[variant] = this
        }) {
            cache[variant] = null
        })
    }

}