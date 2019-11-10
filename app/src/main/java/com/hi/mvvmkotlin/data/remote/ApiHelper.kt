package com.hi.mvvmkotlin.data.remote

import com.hi.mvvmkotlin.data.model.api.FakeDataBean
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Vishal Patel on 11/10/19.
 */
interface ApiHelper {

    @GET("/photos")
    fun doFetchFakeDataListAsync(): Call<List<FakeDataBean>>?
}