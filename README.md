![](https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/master/remote-compose/src/main/assets/dynamic.png)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)  ![](https://img.shields.io/badge/Jetpack%20Compose-1.0.1-brightgreen)  [![](https://jitpack.io/v/vvsdevs/AndroidDynamicJetpackCompose.svg)](https://jitpack.io/#vvsdevs/AndroidDynamicJetpackCompose)  [![API](https://img.shields.io/badge/API-19%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=19)   ![CircleCI](https://circleci.com/gh/CymChad/BaseRecyclerViewAdapterHelper/tree/master.svg?style=svg)

# Android Remote Jetpack Compose: Server-Side Driven UI Using Compose

Android Remote Jetpack Compose is a revolutionary library designed for server-side driven UI development in Android applications using Jetpack Compose. It allows developers to dynamically render layouts from JSON configurations, enabling seamless updates to app interfaces without releasing new versions. This flexibility is ideal for rapidly evolving projects, offering robust support for dynamic UI design and server-side integration.

## Few of Basic Designs that we did using this library 

<p align="center">
  <img src="https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/master/remote-compose/src/main/assets/zepto.png" alt="Home Design" width="200"/>
  <img src="https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/master/remote-compose/src/main/assets/home.png" alt="Home Design" width="200"/>
  <img src="https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/master/remote-compose/src/main/assets/player.png" alt="Player Design" width="200"/>
  <img src="https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/master/remote-compose/src/main/assets/login.png" alt="Login Design" width="200"/>
 <video width="320" height="240" controls>
    <source src="https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/master/remote-compose/src/main/assets/ServerSideDrivenUI.webm" type="video/webm">
    Your browser does not support the video tag.
  </video>
</p>

## Why Choose Android Remote Jetpack Compose?

- Server-Side Driven UI (SSDU): Effortlessly update your app's UI by managing configurations on the server, reducing app downtime and deployment delays.
- Jetpack Compose-Based Dynamic Layouts: Harness the power of Jetpack Compose to create dynamic, reactive, and efficient UI components driven by JSON.
- Flexibility for Modern App Development: Tailor your app's design instantly using a server-side approach, perfect for apps with frequent updates or personalized content.
- Seamless Integration with Server APIs: Use Retrofit to fetch JSON configurations directly from your server, enabling real-time UI updates without app updates.

## Key Features
- Dynamic JSON-Driven UI: Modify and manage layouts from the server without updating the app.
- Support for a Wide Range of Compose Components: From basic elements like Text and Button to advanced layouts like LazyColumn and LazyRow.
- Material Design 3 Compliance: Build modern and stylish UIs with Material Design 3 integration.
- Lightweight Architecture: Designed to minimize overhead and maximize flexibility, leveraging Hilt for dependency injection.
- Customizable and Scalable: Adapt to changing design needs with ease.

## Why Server-Side Driven UI?
Server-Side Driven UI (SSDU) is the future of mobile development, allowing for centralized UI management:

- Real-Time Updates: Make UI changes instantly by updating server configurations.
- Reduced App Update Cycles: No need to publish new app versions for design tweaks.
- Personalized Experiences: Dynamically adjust layouts and content for individual users based on server-driven logic.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
    - [Setting Up Configuration](#setting-up-configuration)
    - [Loading a Screen](#loading-a-screen)
    - [Event Listeners](#event-listeners)
- [Components](#components)
- [Customization](#Manual)
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
        implementation ("com.github.vvsdevs:AndroidDynamicJetpackCompose:1.9.0") 
       //Optional
        implementation("com.google.dagger:hilt-android:2.51.1")
        kapt("com.google.dagger:hilt-compiler:2.51.1")
        implementation("androidx.navigation:navigation-compose:2.8.2")
        implementation("androidx.activity:activity-compose:1.9.2") 
    }
    ```

## Usage

### Setting Up Configuration

Create a configuration for your layout using the `RemoteComposeConfig` class:
```kotlin
val config = RemoteComposeConfig(
    baseUrl = "https://your-server-url.com/",
    uiComponentPath = "compose.json",
    screenPath = "compose_screen1.json"
)
```

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
private var uiComponent by mutableStateOf<UIComponent?>(null)
private var errorMessage by mutableStateOf("")

override fun showError(message: String) {
  this.errorMessage = message
  Log.e("MainActivity", "Error: $message")
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

override fun showComponents(components: UIComponent) {
  uiComponent = components
  Log.d("MainActivity", "Components loaded:  $components")
}

//customization
@Composable
fun MyApp() {
  DynamicLayout(uiComponent, navController, mainPresenter)
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
```

### Event Listeners

3. Event listeners are defined in JSON and handled using the handleAction function in your Kotlin code.

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

# Android Remote Jetpack Compose Manual

## Table of Contents
* [Introduction](#introduction)
* [Components](#components)
  * [ContainerComponent](#containercomponent)
  * [ScreenComponent](#screencomponent)
  * [TextComponent](#textcomponent)
  * [ButtonComponent](#buttoncomponent)
  * [ImageComponent](#imagecomponent)
  * [CardComponent](#cardcomponent)
  * [ColumnComponent](#columncomponent)
  * [RowComponent](#rowcomponent)
  * [LazyColumnComponent](#lazycolumncomponent)
  * [LazyRowComponent](#lazyrowcomponent)
  * [ScrollViewComponent](#scrollviewcomponent)
  * [SpacerComponent](#spacercomponent)
  * [BoxComponent](#boxcomponent)
  * [TextFieldComponent](#textfieldcomponent)
  * [DividerComponent](#dividercomponent)
  * [IconComponent](#iconcomponent)
  * [SwitchComponent](#switchcomponent)
  * [CheckboxComponent](#checkboxcomponent)
  * [SliderComponent](#slidercomponent)
  * [ProgressBarComponent](#progressbarcomponent)
  * [FloatingActionButtonComponent](#floatingactionbuttoncomponent)
* [ModifierData](#modifierdata)
* [Event Handling](#event-handling)
* [Applying Modifiers](#applying-modifiers)

---

## Introduction
This manual provides detailed information on how to use and configure the components available in the Android Remote Jetpack Compose library. Each component is customizable with various properties and modifiers to fit your needs.

## Components

### ContainerComponent
A container that holds multiple components.

- **screens**: List of `ScreenComponent` objects.
- **children**: List of `UIComponent` objects inside the container.
- **modifier**: Optional `ModifierData` for styling.

### ScreenComponent
Represents a screen that holds multiple components.

- **id**: Unique identifier for the screen.
- **children**: List of `UIComponent` objects inside the screen.
- **modifier**: Optional `ModifierData` for styling.

### TextComponent
Displays a piece of text with customization options.

- **text**: The text to display.
- **modifier**: Optional `ModifierData`.
- **color**: Text color in hex.
- **fontSize**: Font size.
- **fontStyle**: Style (e.g., italic, normal).
- **textAlign**: Text alignment (e.g., center, left, right).

### ButtonComponent
A customizable button.

- **text**: The text on the button.
- **action**: The action triggered when the button is clicked.
- **modifier**: Optional `ModifierData`.
- **backgroundColor**: Background color of the button.
- **textColor**: Text color.
- **elevation**: Elevation of the button.

### ImageComponent
Displays an image from a URL.

- **imageUrl**: URL of the image.
- **modifier**: Optional `ModifierData`.
- **contentDescription**: Description for accessibility.
- **contentScale**: How the image is scaled (e.g., fitXY, centerCrop).

### CardComponent
A card that can contain other components.

- **content**: The child component within the card.
- **modifier**: Optional `ModifierData`.
- **backgroundColor**: Background color of the card.
- **elevation**: Elevation/shadow depth.
- **borderColor**: Border color (optional).
- **navigationTarget**: Target screen to navigate when clicked.

### ColumnComponent
Organizes children vertically.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.
- **verticalArrangement**: Spacing arrangement.
- **horizontalAlignment**: Alignment of children.

### RowComponent
Organizes children horizontally.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.
- **horizontalArrangement**: Spacing arrangement.
- **verticalAlignment**: Alignment of children.

### LazyColumnComponent
A scrollable column for large data sets.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.

### LazyRowComponent
A scrollable row for large data sets.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.

### ScrollViewComponent
A scrollable view that contains multiple components.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.

### SpacerComponent
Adds space between components.

- **height**: Height of the spacer.
- **modifier**: Optional `ModifierData`.

### BoxComponent
A flexible container that can hold components.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.
- **contentAlignment**: Alignment of content inside the box.

### TextFieldComponent
An editable text field.

- **hint**: Placeholder text.
- **inputType**: Type of input (e.g., text, number).
- **modifier**: Optional `ModifierData`.
- **value**: Current text value.
- **onValueChange**: Action triggered when text changes.

### DividerComponent
Adds a divider line.

- **modifier**: Optional `ModifierData`.
- **color**: Color of the divider.
- **thickness**: Thickness of the line.

### IconComponent
Displays an icon.

- **iconName**: Name or identifier of the icon.
- **modifier**: Optional `ModifierData`.

### SwitchComponent
A switch that toggles between two states.

- **isChecked**: Initial state of the switch.
- **onCheckedChangeAction**: Action when the state changes.
- **modifier**: Optional `ModifierData`.

### CheckboxComponent
A checkbox for selection.

- **isChecked**: Initial state of the checkbox.
- **onCheckedChangeAction**: Action when the state changes.
- **modifier**: Optional `ModifierData`.

### SliderComponent
A slider for selecting a value within a range.

- **value**: Initial value of the slider.
- **onValueChangeAction**: Action when the value changes.
- **modifier**: Optional `ModifierData`.
- **min**: Minimum value.
- **max**: Maximum value.

### ProgressBarComponent
Displays a progress bar.

- **progress**: Current progress value.
- **modifier**: Optional `ModifierData`.
- **color**: Color of the progress bar.

### FloatingActionButtonComponent
A floating button for actions.

- **icon**: Icon for the button.
- **action**: Action when the button is clicked.
- **modifier**: Optional `ModifierData`.
- **backgroundColor**: Background color.
- **elevation**: Elevation of the button.

## ModifierData
Defines the visual appearance and layout.

- **padding**: Padding in dp.
- **height**: Height of the component.
- **width**: Width of the component.
- **fillMaxHeight**: Boolean to fill maximum height.
- **fillMaxWidth**: Boolean to fill maximum width.
- **backgroundColor**: Background color (hex).
- **cornerRadius**: Corner radius for rounded edges.
- **elevation**: Elevation for shadow effect.

## Event Handling
Components like buttons and switches can have actions associated with them. Actions can be predefined functions or navigational events.

## Applying Modifiers
Modifiers customize the appearance of components. You can combine multiple modifiers to create complex layouts.

---

This manual provides an overview of each component and its usage in the Android Remote Jetpack Compose library. Adjust components according to your UI needs for a dynamic and interactive user experience.



## Manual

For advanced usage, take a look at this awesome [manual](https://github.com/vvsdevs/AndroidDynamicJetpackCompose/blob/master/MANUAL.md).

### Contributing
We welcome contributions to Android Remote Jetpack Compose! If you want to contribute:

	1.	Fork the repository.
	2.	Create a new feature branch.
	3.	Make your changes and submit a pull request.

Feel free to open issues or pull requests on GitHub for any bug fixes or enhancements.

## License
This project is licensed under the [Apache 2.0 License](https://opensource.org/licenses/Apache-2.0).

