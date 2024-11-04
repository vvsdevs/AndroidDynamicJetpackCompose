package com.android.dynamicjetpackcompose.network

import com.android.dynamicjetpackcompose.model.UIComponent
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{path}")
    suspend fun getUIComponent(@Path("path") path: String): UIComponent

    @GET("{path}")
    suspend fun getScreens(@Path("path") path: String): UIComponent
}
