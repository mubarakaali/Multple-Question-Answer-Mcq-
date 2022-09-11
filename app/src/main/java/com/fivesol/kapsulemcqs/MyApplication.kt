package com.fivesol.kapsulemcqs

import android.app.Application
import com.googleads.AdsManagement
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication:Application() {

    @Inject
    lateinit var adsManagement: AdsManagement
    override fun onCreate() {
        super.onCreate()
        adsManagement.init()
    }
}