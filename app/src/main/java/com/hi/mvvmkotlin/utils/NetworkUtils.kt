package com.hi.mvvmkotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

/**
 * Created by Vishal Patel on 11/10/19.
 */
object NetworkUtils {

    @SuppressLint("ObsoleteSdkInt")
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val n: Network = cm.activeNetwork
        val nc: NetworkCapabilities = cm.getNetworkCapabilities(n)
        return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
            NetworkCapabilities.TRANSPORT_WIFI
        )
    }
}