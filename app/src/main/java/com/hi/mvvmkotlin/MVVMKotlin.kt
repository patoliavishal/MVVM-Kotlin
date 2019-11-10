package com.hi.mvvmkotlin

import android.app.Activity
import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.hi.mvvmkotlin.di.component.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Vishal Patel on 11/10/19.
 */
open class MVVMKotlin : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    companion object {
        private lateinit var mInstance: MVVMKotlin
        @Synchronized
        fun getInstance(): MVVMKotlin {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this

        DaggerAppComponent
            .builder()
            .application(this)
            .build().inject(this)

        //Networking Library
        AndroidNetworking.initialize(this)

        //Facebook Database Viewer
        Stetho.initializeWithDefaults(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}
