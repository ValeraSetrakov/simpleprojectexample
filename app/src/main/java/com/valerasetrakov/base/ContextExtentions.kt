package com.valerasetrakov.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.res.ResourcesCompat

fun Context.startActivityClearStack(intent: Intent) {
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
}

fun Context.getRawPath(rawId: Int) = "rawresource:///$rawId"//"android.resource://$packageName/$rawId"

fun Context.getRawUri(rawId: Int) =
    Uri.parse(getRawPath(rawId))

fun Context.getCompatColor(colorResourceId: Int) =
    ResourcesCompat.getColor(resources, colorResourceId, theme)