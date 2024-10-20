package com.android.dynamicJetpackCompose.network

import com.android.dynamicJetpackCompose.model.UIComponent
import com.android.dynamicJetpackCompose.presenter.MainPresenter
import com.android.dynamicJetpackCompose.utils.RemoteComposeConfig
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
            baseUrl = "https://deharve.com/",
            uiComponentPath = "custom_component.json",
            screenPath = "custom_screen.json",
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