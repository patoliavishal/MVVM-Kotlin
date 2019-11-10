package com.hi.mvvmkotlin.ui.signup

import com.hi.mvvmkotlin.ui.base.BaseNavigator

/**
 * Created by Vishal Patel on 11/10/19.
 */
interface SignUpNavigator : BaseNavigator {

    fun signUp()

    fun signIn()

    fun signUpSuccess(message: String)
}