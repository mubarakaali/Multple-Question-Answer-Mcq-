package com.fivesol.kapsulemcqs.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.fivesol.kapsulemcqs.R
import com.fivesol.kapsulemcqs.presentation.main_category.MainActivity
import com.googleads.AdsManagement
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var adsManagement: AdsManagement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        showOpenAds()
    }

    private fun showOpenAds() {
        Handler(Looper.myLooper()!!).postDelayed({
            adsManagement.createOpenAd {
                onAdDismiss = {
                    nextActivity()
                }
                onAdLoaded = {
                    nextActivity()
                    Log.d("jejeje ", "showOpenAds: onAdLoaded")
                }
            }.showCached(this)
        }, 3000)

    }

    private fun nextActivity() {
        val newIntent =
            Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(newIntent)
        finish()
    }
}