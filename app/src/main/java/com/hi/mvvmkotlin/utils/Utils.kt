package com.hi.mvvmkotlin.utils

import android.content.Context
import android.util.DisplayMetrics
import kotlin.math.roundToInt

/**
 * Created by Vishal Patel on 11/10/19.
 */
object Utils {

    fun dpToPx(context: Context, dp: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
}
