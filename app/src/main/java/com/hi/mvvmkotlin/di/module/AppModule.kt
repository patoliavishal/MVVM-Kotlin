package com.hi.mvvmkotlin.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.hi.mvvmkotlin.data.AppDataManager
import com.hi.mvvmkotlin.data.DataManager
import com.hi.mvvmkotlin.data.local.db.AppDatabase
import com.hi.mvvmkotlin.data.local.db.AppDbHelper
import com.hi.mvvmkotlin.data.local.db.DbHelper
import com.hi.mvvmkotlin.data.remote.ApiHelper
import com.hi.mvvmkotlin.data.remote.AppApiHelper
import com.hi.mvvmkotlin.di.DatabaseInfo
import com.hi.mvvmkotlin.di.PreferenceInfo
import com.hi.mvvmkotlin.utils.AppConstants
import com.hi.mvvmkotlin.utils.rx.AppSchedulerProvider
import com.hi.mvvmkotlin.utils.rx.SchedulerProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @PreferenceInfo
    internal fun provideprefFileName(): String = AppConstants.PREF_NAME

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}