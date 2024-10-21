![](https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/main/remote-compose/src/main/assets/dynamic.png)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)  ![](https://img.shields.io/badge/Jetpack%20Compose-1.0.1-brightgreen)  [![](https://jitpack.io/v/vvsdevs/AndroidDynamicJetpackCompose.svg)](https://jitpack.io/#vvsdevs/AndroidDynamicJetpackCompose)  [![API](https://img.shields.io/badge/API-19%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=19)   ![CircleCI](https://circleci.com/gh/CymChad/BaseRecyclerViewAdapterHelper/tree/master.svg?style=svg)

# Android Remote Jetpack Compose

Android Dynamic Jetpack Compose is a powerful library that enables dynamic layout rendering based on JSON configurations using Jetpack Compose. This library allows developers to design and update UI elements dynamically without needing to release a new app update, making it a flexible and efficient solution for Android applications.

## Few of Basic Designs that we did using this library 
![](https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/main/remote-compose/src/main/assets/home.png) ![](https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/main/remote-compose/src/main/assets/player.png) ![](https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/main/remote-compose/src/main/assets/login.png)

## Features
- Dynamic rendering of Jetpack Compose layouts from JSON configurations.
- Support for a wide range of UI components like Text, Image, Button, ScrollView, Card, Column, and more.
- Integration with Retrofit for fetching JSON layout data from a remote server.
- Lightweight and easy-to-integrate using Hilt for dependency injection.
- Support for Material3 components and custom theming.

## Overview
**Android Remote Jetpack Compose** is a dynamic layout rendering solution for Android using Jetpack Compose. This library allows developers to define UI components and layouts through JSON files, making it easy to update UI elements dynamically without needing an app update. It leverages Kotlin, Jetpack Compose, and dependency injection through Hilt, providing a flexible and modular architecture.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
    - [Setting Up Configuration](#setting-up-configuration)
    - [Loading a Screen](#loading-a-screen)
    - [Event Listeners](#event-listeners)
- [Components](#components)
    - [Text](#text)
    - [Button](#button)
    - [Image](#image)
    - [LazyRow](#lazyrow)
    - [Column](#column)
    - [Card](#card)
    - [Switch](#switch)
    - [Slider](#slider)
- [Modifiers](#modifiers)
- [Customization](#customization)
- [License](#license)

## Installation

1. Add the Jitpack repository to your root `build.gradle` file:
    ```groovy
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    ```

2. Add the dependency to your module's `build.gradle` file:
    ```groovy
    dependencies {
        implementation ("com.github.vvsdevs:AndroidDynamicJetpackCompose:1.0.0")
   
        implementation ("com.google.dagger:hilt-android:2.51.1")
        kapt ("com.google.dagger:hilt-compiler:2.51.1")
        implementation ("androidx.compose.ui:ui:1.5.1")
        implementation ("androidx.navigation:navigation-compose:2.5.3")
    }
    ```

## Usage

### Setting Up Configuration

Create a configuration for your layout using the `RemoteComposeConfig` class:
```kotlin
val config = RemoteComposeConfig(
    baseUrl = "https://your-server-url.com/",
    uiComponentPath = "your_component.json",
    screenPath = "your_screen.json"
)
```
	3.	API Configuration: Set up the RemoteComposeConfig in your Required activity:

```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity(), ComposeCallback {
    @Inject
    lateinit var mainPresenter: MainPresenter

    private val config = RemoteComposeConfig(
        baseUrl = "https://example.com/",
        uiComponentPath = "compose.json",
        screenPath = "compose_screen1.json"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainPresenter.initialize(config)
        mainPresenter.attachView(this)
        mainPresenter.loadUiComponents()
        setContent { MyApp() }
    }
}
````


### Loading a Screen

	1.	Inject the MainPresenter and initialize it:
```kotlin
@Inject
lateinit var mainPresenter: MainPresenter

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mainPresenter.initialize(config)
    mainPresenter.attachView(this)
    mainPresenter.loadUiComponents()
    setContent { MyApp() }
}
```
    2.	Use the DynamicLayout composable to render the screen dynamically:
```kotlin
@Composable
fun MyApp() {
    DynamicLayout(uiComponent, navController, mainPresenter)
}
```

### Event Listeners

    Event listeners are defined in JSON and handled using the handleAction function in your Kotlin code.

```kotlin

fun handleAction(action: String, context: Context, mainPresenter: MainPresenter) {
    when (action) {
        "navigateToScreen" -> { /* Handle screen navigation */ }
        "fetchData" -> { /* Fetch data from the server */ }
        else -> { /* Default action */ }
    }
}
```

## Supported UI Components

- **Text**: Displays text with customizable color, font size, and alignment.
- **Image**: Loads images from URLs with scaling options.
- **Button**: Configurable with actions, background color, and text color.
- **TextField**: Editable input fields for user data entry.
- **Card**: Displays content with elevation and rounded corners.
- **Column/Row**: Organizes child components vertically or horizontally.
- **ScrollView**: Scrollable layout for vertical content.
- **Divider**: Adds a horizontal line between components.
- **Switch/Checkbox**: Toggles for user interactions.
- **LazyColumn**: Optimized for displaying a vertical list of components efficiently.
- **LazyRow**: Optimized for displaying a horizontal list of components efficiently.
- **Spacer**: Adds spacing between components for layout adjustments.
- **Box**: A container component that holds child components with customizable alignment.
- **Slider**: Allows users to select a value from a continuous range.
- **ProgressBar**: Displays a visual representation of progress.
- **FloatingActionButton**: A button with an icon for prominent actions.
- **Container**: Groups multiple components and screens together.
- **Screen**: Holds the content and layout for individual screens.

---
Contributing


## Manual

For advanced usage, take a look at this awesome [manual](https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/main/MANUAL.md).

We welcome contributions to Android Remote Jetpack Compose! If you want to contribute:

	1.	Fork the repository.
	2.	Create a new feature branch.
	3.	Make your changes and submit a pull request.

Feel free to open issues or pull requests on GitHub for any bug fixes or enhancements.

