package com.googleads.error

import com.google.android.gms.ads.LoadAdError

private const val AD_ERROR_CODE_PREFIX = -100
private const val AD_ERROR_DOMAIN = "CUSTOM"

enum class AdErrorCode(val message: String) {
    CACHE_NULL("Cache is null")
}

fun AdErrorCode.toLoadAdError(message: String? = null): LoadAdError {
    return LoadAdError(
        AD_ERROR_CODE_PREFIX + ordinal,
        message ?: this.message,
        AD_ERROR_DOMAIN,
        null,
        null
    )
}