package com.benmohammad.mvvmarchnews.utils

import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager
import kotlinx.android.synthetic.main.fragment_country_list.view.*

object UIHelper {


    fun getDisplaySize(context: Context): Point {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
        val outSize = Point()
        windowManager?.defaultDisplay?.getSize(outSize)
        return outSize
    }

    fun getDisplayShorterSideSize(context: Context): Int{
        val outSize = getDisplaySize(context)
        return if(outSize.x < outSize.y) outSize.x else outSize.y
    }

    fun calcGridColumn(context: Context, cellWidth: Int): Int {
        val displaySize = getDisplaySize(context)
        val width = displaySize.x
        return width / cellWidth
    }

    fun getScreenOrientation(context: Context): Int {
        return if(getScreenWidth(context) < getScreenHeight(context)) {
            Configuration.ORIENTATION_PORTRAIT
        } else {
            Configuration.ORIENTATION_LANDSCAPE
        }
    }

    fun getScreenWidth(context: Context?) : Int {
        return if(context == null) {
            0
        } else {
            getDisplayMetrics(context).widthPixels
        }
    }

    fun getScreenHeight(context: Context?): Int {
        return if(context == null) {
            0
        } else {
            getDisplayMetrics(context).heightPixels
        }
    }

    fun getDisplayMetrics(context: Context): DisplayMetrics {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
        val metrics = DisplayMetrics()
        windowManager?.defaultDisplay?.getMetrics(metrics)
        return metrics
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        var resourceId = context.resources
            .getIdentifier("status_bar_height", "dimen", "android")
        if(resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}