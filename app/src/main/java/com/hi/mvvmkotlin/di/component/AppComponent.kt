package com.hi.mvvmkotlin.di.component

import android.app.Application
import com.hi.mvvmkotlin.MVVMKotlin
import com.hi.mvvmkotlin.di.builder.ActivityBuilder
import com.hi.mvvmkotlin.di.builder.FragmentBuilder
import com.hi.mvvmkotlin.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class), (ActivityBuilder::class), (AppModule::class), (FragmentBuilder::class)])
interface AppComponent {

    fun inject(kotlin: MVVMKotlin)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}