package com.hi.mvvmkotlin.data.remote

import com.hi.mvvmkotlin.data.model.api.FakeDataBean
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vishal Patel on 11/10/19.
 */
@Singleton
class AppApiHelper @Inject
constructor() : ApiHelper {

    companion object {

        private fun getServiceApi(retrofit: Retrofit): ApiHelper =
            retrofit.create(ApiHelper::class.java)

        var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiService = getServiceApi(getRetrofit())
    }

    override fun doFetchFakeDataListAsync(): Call<List<FakeDataBean>>? {
        return apiService.doFetchFakeDataListAsync()
    }
}