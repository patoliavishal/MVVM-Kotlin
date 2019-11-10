package com.hi.mvvmkotlin.ui.signup

import android.text.TextUtils
import android.util.Patterns
import com.hi.mvvmkotlin.MVVMKotlin
import com.hi.mvvmkotlin.R
import com.hi.mvvmkotlin.data.DataManager
import com.hi.mvvmkotlin.data.model.db.User
import com.hi.mvvmkotlin.ui.base.BaseViewModel
import com.hi.mvvmkotlin.utils.rx.SchedulerProvider

/**
 * Created by Vishal Patel on 11/10/19.
 */
class SignUpViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<SignUpNavigator>(dataManager, schedulerProvider) {

    fun signUp() {
        navigator?.signUp()
    }

    fun signIn() {
        navigator?.signIn()
    }

    fun isCheckDetails(email: String, password: String, username: String) {
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            TextUtils.isEmpty(password) || password.length < 6 &&
            TextUtils.isEmpty(username)
        ) {
            navigator?.handleError(throwable = Throwable(MVVMKotlin.getInstance().getString(R.string.invalid_email_pass_username)))
        } else if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            navigator?.handleError(throwable = Throwable(MVVMKotlin.getInstance().getString(R.string.invalid_email)))
        } else if (TextUtils.isEmpty(password) || password.length < 6) {
            navigator?.handleError(throwable = Throwable(MVVMKotlin.getInstance().getString(R.string.invalid_password)))
        } else if (TextUtils.isEmpty(username)) {
            navigator?.handleError(throwable = Throwable(MVVMKotlin.getInstance().getString(R.string.invalid_username)))
        } else {
            val user = User()
            user.email = email
            user.password = password
            user.username = username
            isUserExist(user)
        }
    }

    private fun isUserExist(user: User) {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager
                .doIsUserExist(user.email.toString())
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    if (it == 1)
                        navigator?.handleError(
                            throwable = Throwable(
                                MVVMKotlin.getInstance().getString(
                                    R.string.user_already_available
                                )
                            )
                        )
                    else
                        insertSignUpDetails(user)
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

    private fun insertSignUpDetails(user: User) {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager
                .doInsertSignUpDetails(user)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    navigator?.signUpSuccess(MVVMKotlin.getInstance().getString(R.string.signup_success))
                }, {
                    setIsLoading(false)
                    navigator?.handleError(
                        throwable = Throwable(
                            MVVMKotlin.getInstance().getString(
                                R.string.signup_failure
                            )
                        )
                    )
                })
        )
    }
}