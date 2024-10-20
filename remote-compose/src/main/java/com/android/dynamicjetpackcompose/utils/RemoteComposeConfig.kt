package com.android.dynamicJetpackCompose.utils

data class RemoteComposeConfig(
    val baseUrl: String,
    val uiComponentPath: String = "compose.json",
    val screenPath: String = "compose_screen1.json",
    val loadFrom: String = "local",
)