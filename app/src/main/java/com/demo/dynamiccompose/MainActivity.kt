@file:OptIn(ExperimentalMaterial3Api::class)

package com.demo.dynamiccompose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.dynamicjetpackcompose.compose.DynamicLayout
import com.android.dynamicjetpackcompose.model.UIComponent
import com.android.dynamicjetpackcompose.presenter.ComposeCallback
import com.android.dynamicjetpackcompose.presenter.MainPresenter
import com.android.dynamicjetpackcompose.utils.RemoteComposeConfig
import com.demo.dynamiccompose.ui.theme.AndroidRemoteJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), ComposeCallback {

    private var uiComponent by mutableStateOf<UIComponent?>(null)
    private var errorMessage by mutableStateOf("")

    @Inject
    lateinit var mainPresenter: MainPresenter

    private val config = RemoteComposeConfig(
        baseUrl = "https://https://raw.githubusercontent.com/vvsdevs/AndroidDynamicJetpackCompose/refs/tags/v1.3.0/remote-compose/src/main/assets/",
        uiComponentPath = "compose.json",
        screenPath = "compose_screen1.json"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainPresenter.initialize(config)
        mainPresenter.attachView(this)
        mainPresenter.loadUiComponents()
        setContent {
            AndroidRemoteJetpackComposeTheme {
                MyApp()
            }
        }
    }

    override fun showError(message: String) {
        this.errorMessage = message
        Log.e("MainActivity", "Error: $message")
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showComponents(components: UIComponent) {
        uiComponent = components
        Log.d("MainActivity", "Components loaded:  $components")
    }

    @Composable
    fun MyApp() {
        val context = LocalContext.current
        val navController = rememberNavController()
        BackHandler(enabled = true) {
            if (!navController.popBackStack()) {
                (context as? ComponentActivity)?.finish()
            }
        }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Top Bar") },
                    actions = {
                        // Add any actions if needed
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { mainPresenter.loadUiComponents() }) {
                    Text("+")
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavHost(navController, startDestination = "mainScreen") {
                    composable("mainScreen") {
                        uiComponent?.let {
                            DynamicLayout(it, navController, mainPresenter)
                        }
                    }
                    composable("navigate/{screenId}") { backStackEntry ->
                        val screenId = backStackEntry.arguments?.getString("screenId")
                        screenId?.let {
                            LaunchedEffect(Unit) {
                                mainPresenter.loadScreenById(it) { success ->
                                    if (!success) {
                                        Toast.makeText(
                                            context,
                                            "Design for $screenId not found",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                        uiComponent?.let {
                            DynamicLayout(it, navController, mainPresenter)
                        }
                    }
                }
            }
        }
    }
}