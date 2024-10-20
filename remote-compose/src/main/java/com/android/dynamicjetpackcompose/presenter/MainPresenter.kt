package com.android.dynamicJetpackCompose.presenter

import android.util.Log
import com.android.dynamicJetpackCompose.model.UIComponent
import com.android.dynamicJetpackCompose.network.ApiService
import com.android.dynamicJetpackCompose.utils.RemoteComposeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface ComposeCallback {
    fun showError(message: String)
    fun showComponents(components: UIComponent)
}

class MainPresenter @Inject constructor(
    private val apiService: ApiService
) {
    private var config: RemoteComposeConfig? = null
    private var view: ComposeCallback? = null
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun attachView(view: ComposeCallback) {
        this.view = view
    }

    fun initialize(config: RemoteComposeConfig) {
        this.config = config
        Log.d("MainPresenter", "Configuration initialized with baseUrl: ${config.baseUrl}")
    }


    fun loadUiComponents() {
        val path = config?.uiComponentPath ?: "default_component.json"
        coroutineScope.launch {
            try {
                val components = apiService.getUIComponent(path)
                Log.d("MainPresenter", "Components loaded: $components")
                withContext(Dispatchers.Main) {
                    view?.showComponents(components)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view?.showError("Error loading components: ${e.message}")
                }
            }
        }
    }

    fun loadScreenById(screenId: String, callback: (Boolean) -> Unit) {
        val path = config?.screenPath ?: "default_screen.json"
        Log.d("MainPresenter", "Loading screen with ID: $screenId from $path")
        coroutineScope.launch {
            try {
                val screenResponse = apiService.getScreens(path)
                val screenComponent = findScreenComponent(screenId, screenResponse)

                withContext(Dispatchers.Main) {
                    if (screenComponent != null) {
                        view?.showComponents(screenComponent)
                        callback(true)
                    } else {
                        callback(false)
                        view?.showError("Screen not found for ID: $screenId")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view?.showError("Error loading screen: ${e.message}")
                    callback(false)
                }
            }
        }
    }

    private fun findScreenComponent(
        screenId: String,
        component: UIComponent
    ): UIComponent.ScreenComponent? {
        when (component) {
            is UIComponent.ScreenComponent -> {
                if (component.id == screenId) return component
            }

            is UIComponent.ScrollViewComponent -> {
                component.screens.find { it.id == screenId }?.let { return it }
                component.children.forEach { child ->
                    val result = findScreenComponent(screenId, child)
                    if (result != null) return result
                }
            }

            else -> Log.d("MainPresenter", "Component not found")
        }
        return null
    }
}