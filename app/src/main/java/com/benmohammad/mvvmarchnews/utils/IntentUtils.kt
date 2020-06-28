package com.benmohammad.mvvmarchnews.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.app.ShareCompat

fun intentShareText(activity: Activity, text: String) {
    val shareIntent = ShareCompat.IntentBuilder.from(activity)
        .setText(text)
        .setType("text/plain")
        .createChooserIntent()
        .apply {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            } else {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
            }
        }
    activity.startActivity(shareIntent)
}

fun intentOpenWebsite(activity: Activity, url: String) {
    val openUrl = Intent(Intent.ACTION_VIEW)
    openUrl.data = Uri.parse(url)
    activity.startActivity(openUrl)
}