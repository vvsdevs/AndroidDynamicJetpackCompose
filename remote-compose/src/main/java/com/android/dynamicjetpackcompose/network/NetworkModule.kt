package com.android.dynamicjetpackcompose.network

import com.android.dynamicjetpackcompose.model.UIComponent
import com.android.dynamicjetpackcompose.presenter.MainPresenter
import com.android.dynamicjetpackcompose.utils.RemoteComposeConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(UIComponent::class.java, UIComponentDeserializer())
            .create()
    }

    @Provides
    @Singleton
    fun provideRemoteComposeConfig(): RemoteComposeConfig {
        return RemoteComposeConfig(
            baseUrl = "https://https://raw.githubusercontent.com/vvsdevs/AndroidDynamicJetpackCompose/refs/tags/v1.3.0/remote-compose/src/main/assets/",
            uiComponentPath = "compose.json",
            screenPath = "compose_screen1.json",
            loadFrom = "remote"
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, config: RemoteComposeConfig): Retrofit {
        return Retrofit.Builder()
            .baseUrl(config.baseUrl) // Use the base URL from the configuration
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMainPresenter(apiService: ApiService): MainPresenter {
        return MainPresenter(apiService)
    }
}