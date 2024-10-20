![](https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/main/remote-compose/src/main/assets/dynamic.png)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)[![](https://img.shields.io/badge/Jetpack%20Compose-1.0.1-brightgreen)] [![](https://jitpack.io/v/vvsdevs/AndroidDynamicJetpackCompose.svg)](https://jitpack.io/#vvsdevs/AndroidDynamicJetpackCompose) [![API](https://img.shields.io/badge/API-19%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=19) ![CircleCI](https://circleci.com/gh/CymChad/BaseRecyclerViewAdapterHelper/tree/master.svg?style=svg)

# Android Remote Jetpack Compose

Android Dynamic Jetpack Compose is a powerful library that enables dynamic layout rendering based on JSON configurations using Jetpack Compose. This library allows developers to design and update UI elements dynamically without needing to release a new app update, making it a flexible and efficient solution for Android applications.

## Features
- Dynamic rendering of Jetpack Compose layouts from JSON configurations.
- Support for a wide range of UI components like Text, Image, Button, ScrollView, Card, Column, and more.
- Integration with Retrofit for fetching JSON layout data from a remote server.
- Lightweight and easy-to-integrate using Hilt for dependency injection.
- Support for Material3 components and custom theming.

## Table of Contents
1. [Installation](#installation)
2. [Setup](#setup)
3. [Usage](#usage)
4. [Customization](#customization)
5. [API Configuration](#api-configuration)
6. [Supported UI Components](#supported-ui-components)
7. [Contributing](#contributing)
8. [License](#license)

## Installation

1. Add repository in root ```build.gradle```

```gradle
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

2. Add the dependency

```gradle
    implementation 'com.github.vvsdevs:AndroidDynamicJetpackCompose:v1.0.0'
```

3. Add below permission in manifest.xml
````
    <uses-permission android:name="android.permission.INTERNET" /> 
````

Setup

	1.	Enable Hilt: Ensure your application class is annotated with @HiltAndroidApp:

````kotlin
@HiltAndroidApp
class RemoteComposeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialization logic here
    }
}
````
    2.	Add Hilt and Compose dependencies in your build.gradle:
        
````java
dependencies {
// Hilt dependencies
implementation 'com.google.dagger:hilt-android:<version>'
kapt 'com.google.dagger:hilt-compiler:<version>'

    // Jetpack Compose dependencies
    implementation "androidx.compose.ui:ui:<version>"
    implementation "androidx.compose.material3:material3:<version>"
}
````
	3.	API Configuration: Set up the RemoteComposeConfig in your Required activity:
````kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity(), ComposeCallback {
    @Inject
    lateinit var mainPresenter: MainPresenter

    private val config = RemoteComposeConfig(
        baseUrl = "https://example.com/",
        uiComponentPath = "components.json",
        screenPath = "screens.json"
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

## Manual

## Available soon...

## Supported UI Components

	•	Text: Displays text with customizable color, font size, and alignment.
	•	Image: Loads images from URLs with scaling options.
	•	Button: Configurable with actions, background color, and text color.
	•	TextField: Editable input fields for user data entry.
	•	Card: Displays content with elevation and rounded corners.
	•	Column/Row: Organizes child components vertically or horizontally.
	•	ScrollView: Scrollable layout for vertical content.
	•	Divider: Adds a horizontal line between components.
	•	Switch/Checkbox: Toggles for user interactions.

Contributing

We welcome contributions to Android Remote Jetpack Compose! If you want to contribute:

	1.	Fork the repository.
	2.	Create a new feature branch.
	3.	Make your changes and submit a pull request.

Feel free to open issues or pull requests on GitHub for any bug fixes or enhancements.

