package com.hi.mvvmkotlin.di.builder

import com.hi.mvvmkotlin.ui.fakedata.FakeDataListActivity
import com.hi.mvvmkotlin.ui.signin.SignInActivity
import com.hi.mvvmkotlin.ui.signup.SignUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindFakeDataActivity(): FakeDataListActivity

    @ContributesAndroidInjector
    abstract fun bindSignInActivity(): SignInActivity

    @ContributesAndroidInjector
    abstract fun bindSignUpActivity(): SignUpActivity
}