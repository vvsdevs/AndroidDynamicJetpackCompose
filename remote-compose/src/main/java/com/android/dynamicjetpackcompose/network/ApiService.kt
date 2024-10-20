package com.android.dynamicJetpackCompose.network

import com.android.dynamicJetpackCompose.model.UIComponent
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{path}")
    suspend fun getUIComponent(@Path("path") path: String): UIComponent

    @GET("{path}")
    suspend fun getScreens(@Path("path") path: String): UIComponent
}
