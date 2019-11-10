package com.hi.mvvmkotlin.data.model.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Vishal Patel on 11/10/19.
 */
data class FakeDataBean(
    @SerializedName("albumId") val albumId: String,
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
) 