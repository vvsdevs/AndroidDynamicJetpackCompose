package com.android.dynamicjetpackcompose.utils

data class RemoteComposeConfig(
    val baseUrl: String,
    val uiComponentPath: String = "compose.json",
    val screenPath: String = "compose_screen1.json",
    val loadFrom: String = "local",
)