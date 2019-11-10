package com.hi.mvvmkotlin.ui.signin

import com.hi.mvvmkotlin.ui.base.BaseNavigator

/**
 * Created by Vishal Patel on 11/10/19.
 */
interface SignInNavigator : BaseNavigator {

    fun signIn()

    fun signUp()

    fun onFacebookSignIn()

    fun onTwitterSignIn()

    fun onGoogleSignIn()

    fun signInSuccess(message: String)
}