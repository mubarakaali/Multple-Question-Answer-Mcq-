package com.googleads

import android.content.Context
import android.view.View
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

object BannerAdsManagement {

    fun bannerAds(context: Context, adview: AdView?) {
        showBannerAd(adview!!)
    }

    private fun showBannerAd(adview: AdView?) {
        val adRequest = AdRequest.Builder().build()
        adview!!.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                adview.visibility = View.VISIBLE
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                adview.visibility = View.INVISIBLE
            }
        }
        adview.loadAd(adRequest)
    }


}