package com.googleads.ads_creation

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.googleads.ads_type.AppOpenAdsType
import com.googleads.listeners.SubscriptionListener
import java.util.*

private const val TAG = "GoogleAppOpenAd"

class AppOpenAdCreation(private val subscription: SubscriptionListener) {

    companion object {
        val appOpenAdId: String
            get() = AppOpenAdsType.OPEN_ADS.id

        private var cache: AppOpenAdCreation? = null

        internal fun preload(context: Context, subscription: SubscriptionListener) {
            AppOpenAdCreation(subscription).loadCache(context)
        }
    }

    var onAdLoaded: (() -> Unit)? = null
    var onAdDismiss: (() -> Unit)? = null
    var isLandscape: Boolean = false

    @get:Synchronized
    @set:Synchronized
    private var finished: Boolean = false
    private var openAd: AppOpenAd? = null

    private val adRequest: AdRequest = AdRequest.Builder().build()
    private val orientation: Int
        get() {
            return if (isLandscape)
                AppOpenAd.APP_OPEN_AD_ORIENTATION_LANDSCAPE
            else
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT
        }

    private fun getLoadCallback(loadedAd: (AppOpenAd) -> Unit) =
        object : AppOpenAd.AppOpenAdLoadCallback() {
            override fun onAdLoaded(appOpenAd: AppOpenAd) {
                super.onAdLoaded(appOpenAd)
                if (finished)
                    return

                finished = true
                onAdLoaded?.invoke()
                appOpenAd.fullScreenContentCallback = fullScreenContentCallback
                loadedAd(appOpenAd)
            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                super.onAdFailedToLoad(error)
                Log.w(TAG, "onAdFailedToLoad: $error")
                finished = true
                onAdDismiss?.invoke()
            }
        }

    private val fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
            onAdDismiss?.invoke()
        }
    }

    fun showCached(activity: Activity) {
//        if (subscription.getStatus(activity)) {
//            onAdDismiss?.invoke()
//            return
//        }

        cache?.let {
            it.onAdLoaded = onAdLoaded
            it.onAdDismiss = onAdDismiss

            it.openAd?.show(activity) ?: onAdDismiss?.invoke()
        } ?: onAdDismiss?.invoke()

        cache = null
        preload(activity, subscription)
    }

    fun show(activity: Activity) {
//        if (subscription.getStatus(activity)) {
//            onAdDismiss?.invoke()
//            return
//        }

        AppOpenAd.load(activity.baseContext, appOpenAdId, adRequest, orientation, getLoadCallback {
            it.show(activity)
        })

        Timer().schedule(object : TimerTask() {
            override fun run() {
                if (!finished) {
                    finished = true
                    onAdDismiss?.invoke()
                    onAdDismiss = null
                }
                cancel()
            }
        }, 3000)
    }

    // TODO: Instead of assigning to cache here, rename this to load and just set the ad
    private fun loadCache(context: Context) {
        AppOpenAd.load(context, appOpenAdId, adRequest, orientation, getLoadCallback {
            cache = this
            openAd = it
        })
    }

}