package com.hi.mvvmkotlin.ui.base

/**
 * Created by Vishal Patel on 11/10/19.
 */
interface BaseNavigator {

    fun handleError(throwable: Throwable)

    fun noInternetConnection()
}