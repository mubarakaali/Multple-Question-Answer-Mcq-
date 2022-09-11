package com.googleads.listeners

import android.content.Context

fun interface SubscriptionListener {
    fun getStatus(context: Context): Boolean
}