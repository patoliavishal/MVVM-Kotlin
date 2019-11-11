package com.hi.mvvmkotlin.ui.signin

import android.text.TextUtils
import android.util.Patterns
import com.hi.mvvmkotlin.MVVMKotlin
import com.hi.mvvmkotlin.R
import com.hi.mvvmkotlin.data.DataManager
import com.hi.mvvmkotlin.ui.base.BaseViewModel
import com.hi.mvvmkotlin.utils.rx.SchedulerProvider

/**
 * Created by Vishal Patel on 11/10/19.
 */
class SignInViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<SignInNavigator>(dataManager, schedulerProvider) {

    fun signIn() {
        navigator?.signIn()
    }

    fun signUp() {
        navigator?.signUp()
    }

    fun onFacebookSignInClick() {
        navigator?.onFacebookSignIn()
    }

    fun onTwitterSignInClick() {
        navigator?.onTwitterSignIn()
    }

    fun onGoogleSignInClick() {
        navigator?.onGoogleSignIn()
    }

    fun isCheckEmailPassword(email: String, password: String) {
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            navigator?.handleError(throwable = Throwable(MVVMKotlin.getInstance().getString(R.string.invalid_email_password)))
        } else if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            navigator?.handleError(throwable = Throwable(MVVMKotlin.getInstance().getString(R.string.invalid_email)))
        } else if (TextUtils.isEmpty(password) || password.length < 6) {
            navigator?.handleError(throwable = Throwable(MVVMKotlin.getInstance().getString(R.string.invalid_password)))
        } else {
            isUserExist(email, password)
        }
    }

    private fun isUserExist(email: String, password: String) {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager
                .doIsUserExist(email)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    if (it == 1)
                        validateSignIn(email, password)
                    else
                        navigator?.handleError(
                            throwable = Throwable(
                                MVVMKotlin.getInstance().getString(
                                    R.string.user_not_available
                                )
                            )
                        )
                }, {
                    setIsLoading(false)
                    navigator?.handleError(
                        throwable = Throwable(
                            MVVMKotlin.getInstance().getString(
                                R.string.something_want_wrong
                            )
                        )
                    )
                })
        )
    }

    private fun validateSignIn(email: String, password: String) {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager
                .doValidateSignIn(email, password)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    navigator?.signInSuccess(MVVMKotlin.getInstance().getString(R.string.signin_success))
                }, {
                    setIsLoading(false)
                    navigator?.handleError(
                        throwable = Throwable(
                            MVVMKotlin.getInstance().getString(
                                R.string.signin_failure
                            )
                        )
                    )
                })
        )
    }
}