package com.fivesol.kapsulemcqs.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.fivesol.kapsulemcqs.R
import com.fivesol.kapsulemcqs.presentation.main_category.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        showOpenAds()
    }

    private fun showOpenAds() {
        Handler(Looper.myLooper()!!).postDelayed({
            nextActivity()
        }, 3000)

    }

    private fun nextActivity() {
        val newIntent =
            Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(newIntent)
        finish()
    }
}