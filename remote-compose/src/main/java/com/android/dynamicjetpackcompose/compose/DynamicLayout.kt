package com.android.dynamicJetpackCompose.compose

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.android.dynamicJetpackCompose.model.ModifierData
import com.android.dynamicJetpackCompose.model.UIComponent
import com.android.dynamicJetpackCompose.presenter.MainPresenter

@Composable
fun DynamicLayout(
    uiComponent: UIComponent,
    navController: NavController,
    mainPresenter: MainPresenter
) {
    // Log.d("DynamicLayout", "Rendering component: ${uiComponent::class.simpleName}")
    val context = LocalContext.current
    when (uiComponent) {
        is UIComponent.TextComponent -> DynamicText(
            text = uiComponent.text,
            modifierData = uiComponent.modifier,
            colorHex = uiComponent.color,
            fontSize = uiComponent.fontSize,
            textAlign = uiComponent.textAlign,
            navController = navController
        )

        is UIComponent.ButtonComponent -> DynamicButton(
            text = uiComponent.text,
            onClickAction = { handleAction(uiComponent.action, context, mainPresenter) },
            modifierData = uiComponent.modifier,
            backgroundColor = uiComponent.backgroundColor,
            textColor = uiComponent.textColor,
            navController = navController
        )

        is UIComponent.ColumnComponent -> DynamicColumn(
            children = uiComponent.children,
            modifierData = uiComponent.modifier,
            navController = navController,
            mainPresenter = mainPresenter
        )

        is UIComponent.RowComponent -> DynamicRow(
            children = uiComponent.children,
            modifierData = uiComponent.modifier,
            navController = navController,
            mainPresenter = mainPresenter
        )

        is UIComponent.LazyColumnComponent -> DynamicLazyColumn(
            children = uiComponent.children,
            navController = navController,
            mainPresenter = mainPresenter
        )

        is UIComponent.LazyRowComponent -> DynamicLazyRow(
            children = uiComponent.children,
            navController = navController,
            mainPresenter = mainPresenter
        )

        is UIComponent.ScrollViewComponent -> DynamicScrollView(
            children = uiComponent.children,
            modifierData = uiComponent.modifier,
            navController = navController,
            mainPresenter = mainPresenter,
        )

        is UIComponent.CardComponent -> uiComponent.content?.let {
            DynamicCard(
                content = it,
                modifierData = uiComponent.modifier,
                backgroundColor = uiComponent.backgroundColor,
                elevation = uiComponent.elevation,
                shape = uiComponent.shape,
                navController = navController,
                mainPresenter = mainPresenter,
                onClickAction = {
                    Log.d("ererere", "DynamicLayout:  clicked-->" + uiComponent.navigationTarget)
                    uiComponent.navigationTarget?.let { navigationTarget ->
                        navController.navigate("navigate/$navigationTarget")
                    }
                }
            )
        }

        is UIComponent.ImageComponent -> DynamicImage(
            imageUrl = uiComponent.imageUrl,
            modifierData = uiComponent.modifier,
            contentScaleType = uiComponent.contentScale,
            navController = navController
        )

        is UIComponent.SpacerComponent -> DynamicSpacer(
            height = uiComponent.height,
            navController = navController
        )

        is UIComponent.DividerComponent -> DynamicDivider(
            modifierData = uiComponent.modifier,
            navController = navController
        )

        is UIComponent.BoxComponent -> DynamicBox(
            children = uiComponent.children,
            modifierData = uiComponent.modifier,
            navController = navController,
            mainPresenter = mainPresenter
        )

        is UIComponent.TextFieldComponent -> DynamicTextField(
            hint = uiComponent.hint,
            inputType = uiComponent.inputType,
            value = uiComponent.value,
            onValueChangeAction = {
                handleAction(
                    uiComponent.onValueChange,
                    context,
                    mainPresenter
                )
            },
            modifierData = uiComponent.modifier,
            navController = navController
        )

        is UIComponent.IconComponent -> DynamicIcon(
            iconName = uiComponent.iconName,
            modifierData = uiComponent.modifier
        )

        is UIComponent.SwitchComponent -> DynamicSwitch(
            isChecked = uiComponent.isChecked,
            onCheckedChangeAction = {
                handleAction(
                    uiComponent.onCheckedChangeAction,
                    context,
                    mainPresenter
                )
            },
            modifierData = uiComponent.modifier,
            navController = navController
        )

        is UIComponent.CheckboxComponent -> DynamicCheckbox(
            isChecked = uiComponent.isChecked,
            onCheckedChangeAction = {
                handleAction(
                    uiComponent.onCheckedChangeAction,
                    context,
                    mainPresenter
                )
            },
            modifierData = uiComponent.modifier,
            navController = navController
        )

        is UIComponent.SliderComponent -> DynamicSlider(
            value = uiComponent.value,
            onValueChangeAction = {
                handleAction(
                    uiComponent.onValueChangeAction,
                    context,
                    mainPresenter
                )
            },
            modifierData = uiComponent.modifier,
            navController = navController
        )

        is UIComponent.ProgressBarComponent -> DynamicProgressBar(
            progress = uiComponent.progress,
            modifierData = uiComponent.modifier,
            navController = navController
        )

        is UIComponent.FloatingActionButtonComponent -> DynamicFloatingActionButton(
            icon = uiComponent.icon,
            onClickAction = { handleAction(uiComponent.action, context, mainPresenter) },
            modifierData = uiComponent.modifier,
            navController = navController
        )

        is UIComponent.ContainerComponent -> DynamicContainer(
            children = uiComponent.children,
            modifierData = uiComponent.modifier,
            navController = navController,
            mainPresenter = mainPresenter
        )

        is UIComponent.ScreenComponent -> DynamicScreen(
            screenComponent = uiComponent,
            navController = navController,
            mainPresenter = mainPresenter
        )
    }
}


// Composable for ScrollView
@Composable
fun DynamicScrollView(
    children: List<UIComponent>,
    modifierData: ModifierData?,
    navController: NavController,
    mainPresenter: MainPresenter,
) {
    val modifier = applyModifiers(modifierData).verticalScroll(rememberScrollState())
    Column(modifier = modifier) {
        children.forEach { child ->
            DynamicLayout(child, navController, mainPresenter)
        }
    }
}

@Composable
fun DynamicIcon(iconName: String, modifierData: ModifierData?) {
    // Placeholder implementation for icon
    // You can use an icon library like Icons.Default or load an icon from resources
    val modifier = applyModifiers(modifierData)
    // Replace with actual icon rendering mechanism
    Text(
        text = iconName,
        modifier = modifier
    ) // This is a placeholder. Replace with your icon logic.
}

@Composable
fun DynamicSwitch(
    isChecked: Boolean,
    onCheckedChangeAction: () -> Unit,
    modifierData: ModifierData?,
    trackColor: String? = null,
    thumbColor: String? = null,
    navController: NavController,
) {
    val modifier = applyModifiers(modifierData)
    val trackColorParsed = trackColor?.let { Color(android.graphics.Color.parseColor(it)) }
    val thumbColorParsed = thumbColor?.let { Color(android.graphics.Color.parseColor(it)) }

    Switch(
        checked = isChecked,
        onCheckedChange = { onCheckedChangeAction() },
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedTrackColor = trackColorParsed ?: MaterialTheme.colorScheme.primary,
            checkedThumbColor = thumbColorParsed ?: MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun DynamicCheckbox(
    isChecked: Boolean,
    onCheckedChangeAction: () -> Unit,
    modifierData: ModifierData?,
    color: String? = null,
    navController: NavController,
) {
    val modifier = applyModifiers(modifierData)
    val checkboxColor = color?.let { Color(android.graphics.Color.parseColor(it)) }

    Checkbox(
        checked = isChecked,
        onCheckedChange = { onCheckedChangeAction() },
        modifier = modifier,
        colors = CheckboxDefaults.colors(
            checkedColor = checkboxColor ?: MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun DynamicSlider(
    value: Float,
    onValueChangeAction: (Float) -> Unit,
    modifierData: ModifierData?,
    trackColor: String? = null,
    thumbColor: String? = null,
    min: Float = 0f,
    max: Float = 1f,
    navController: NavController,
) {
    val modifier = applyModifiers(modifierData)
    val trackColorParsed = trackColor?.let { Color(android.graphics.Color.parseColor(it)) }
    val thumbColorParsed = thumbColor?.let { Color(android.graphics.Color.parseColor(it)) }

    Slider(
        value = value,
        onValueChange = onValueChangeAction,
        valueRange = min..max,
        modifier = modifier,
        colors = SliderDefaults.colors(
            thumbColor = thumbColorParsed ?: MaterialTheme.colorScheme.primary,
            activeTrackColor = trackColorParsed ?: MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun DynamicProgressBar(
    progress: Float,
    modifierData: ModifierData?,
    color: String? = null,
    navController: NavController,
) {
    val modifier = applyModifiers(modifierData)
    val progressColor = color?.let { Color(android.graphics.Color.parseColor(it)) }

    LinearProgressIndicator(
        progress = progress,
        modifier = modifier,
        color = progressColor ?: MaterialTheme.colorScheme.primary
    )
}

@Composable
fun DynamicContainer(
    children: List<UIComponent>,
    modifierData: ModifierData?,
    navController: NavController,
    mainPresenter: MainPresenter
) {
    val modifier = applyModifiers(modifierData)
    Column(modifier = modifier) {
        children.forEach { child ->
            DynamicLayout(child, navController, mainPresenter)
        }
    }
}

@Composable
fun DynamicScreen(
    screenComponent: UIComponent.ScreenComponent,
    navController: NavController,
    mainPresenter: MainPresenter
) {
    Column(modifier = applyModifiers(screenComponent.modifier)) {
        screenComponent.children.forEach { child ->
            DynamicLayout(child, navController, mainPresenter)
        }
    }
}

@Composable
fun DynamicFloatingActionButton(
    icon: String,
    onClickAction: () -> Unit,
    modifierData: ModifierData?,
    backgroundColor: String? = null,
    iconTint: String? = null,
    elevation: Float? = null,
    shape: String? = null,
    navController: NavController,
) {
    val modifier = applyModifiers(modifierData)
    val backgroundColorParsed =
        backgroundColor?.let { Color(android.graphics.Color.parseColor(it)) }
    val iconTintColor = iconTint?.let { Color(android.graphics.Color.parseColor(it)) }

    FloatingActionButton(
        onClick = onClickAction,
        modifier = modifier,
        containerColor = backgroundColorParsed ?: MaterialTheme.colorScheme.primary,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = elevation?.dp ?: 6.dp
        ),
        shape = RoundedCornerShape(50) // Adjust shape as needed
    ) {
        // Add icon logic, e.g., using Icons or an image resource
        Text(
            text = icon,
            color = iconTintColor ?: MaterialTheme.colorScheme.onPrimary
        ) // Placeholder
    }
}

@Composable
fun DynamicTextField(
    hint: String,
    inputType: String,
    value: String,
    onValueChangeAction: () -> Unit,
    modifierData: ModifierData?,
    navController: NavController,
) {
    val modifier = applyModifiers(modifierData)
    var text by rememberSaveable { mutableStateOf(value) }
    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChangeAction()
        },
        placeholder = { Text(hint) },
        modifier = modifier
    )
}

@Composable
fun DynamicText(
    text: String,
    modifierData: ModifierData?,
    colorHex: String? = null,
    fontSize: Float? = null,
    textAlign: String? = null,
    navController: NavController,
) {
    val modifier = applyModifiers(modifierData)
    val color = colorHex?.let { Color(android.graphics.Color.parseColor(it)) } ?: Color.Unspecified
    val textAlignParsed = when (textAlign) {
        "center" -> TextAlign.Center
        "left" -> TextAlign.Left
        "right" -> TextAlign.Right
        else -> TextAlign.Start
    }

    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize?.sp ?: TextUnit.Unspecified,
        textAlign = textAlignParsed
    )
}


@Composable
fun DynamicButton(
    text: String,
    onClickAction: () -> Unit,
    modifierData: ModifierData?,
    backgroundColor: String? = null,
    textColor: String? = null,
    navController: NavController,
) {
    val modifier = applyModifiers(modifierData)
    val backgroundColorParsed =
        backgroundColor?.let { Color(android.graphics.Color.parseColor(it)) }
    val textColorParsed = textColor?.let { Color(android.graphics.Color.parseColor(it)) }

    Button(
        onClick = { onClickAction() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColorParsed ?: MaterialTheme.colorScheme.primary,
            contentColor = textColorParsed ?: MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text)
    }
}


@Composable
fun DynamicColumn(
    children: List<UIComponent>, modifierData: ModifierData?,
    navController: NavController,
    mainPresenter: MainPresenter,
) {
    val modifier = applyModifiers(modifierData)
    Column(modifier = modifier) {
        children.forEach { child ->
            DynamicLayout(child, navController, mainPresenter)
        }
    }
}

@Composable
fun DynamicRow(
    children: List<UIComponent>, modifierData: ModifierData?,
    navController: NavController,
    mainPresenter: MainPresenter,
) {
    val modifier = applyModifiers(modifierData)
    Row(modifier = modifier) {
        children.forEach { child ->
            DynamicLayout(child, navController, mainPresenter)
        }
    }
}

@Composable
fun DynamicLazyColumn(
    children: List<UIComponent>,
    navController: NavController,
    mainPresenter: MainPresenter,
) {
    LazyColumn {
        items(children.size) { index ->
            DynamicLayout(children[index], navController, mainPresenter)
        }
    }
}

@Composable
fun DynamicLazyRow(
    children: List<UIComponent>,
    navController: NavController,
    mainPresenter: MainPresenter,
) {
    LazyRow {
        items(children.size) { index ->
            DynamicLayout(children[index], navController, mainPresenter)
        }
    }
}

@Composable
fun DynamicCard(
    content: UIComponent,
    modifierData: ModifierData?,
    backgroundColor: String? = null,
    elevation: Float? = null,
    shape: String? = null,
    navController: NavController,
    mainPresenter: MainPresenter,
    onClickAction: () -> Unit = {}
) {
    val modifier = applyModifiers(modifierData)
    val color = backgroundColor?.let { Color(android.graphics.Color.parseColor(it)) }
        ?: MaterialTheme.colorScheme.surface
    val cardShape: Shape = RoundedCornerShape(shape?.toInt()?.dp ?: 8.dp)
    val navigationTarget = (content as? UIComponent.CardComponent)?.navigationTarget
    val context = LocalContext.current
    Card(
        modifier = modifier.clickable {
            if (navigationTarget != null) {
                Log.d("DynamicLayout", "Navigating to $navigationTarget")
                mainPresenter.loadScreenById(navigationTarget) { success ->
                    if (success) {
                        // Navigate to the screen only if loading was successful
                        // navController.navigate("navigate/$navigationTarget")
                        Toast.makeText(
                            context,
                            "Design for $navigationTarget found",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Design for $navigationTarget not found",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            onClickAction()
        },
        colors = CardDefaults.cardColors(containerColor = color),
        shape = cardShape,
        elevation = CardDefaults.cardElevation(elevation?.dp ?: 14.dp)
    ) {
        DynamicLayout(content, navController, mainPresenter)
    }
}

@Composable
fun DynamicImage(
    imageUrl: String, modifierData: ModifierData?,
    contentScaleType: String? = "fitXY",
    navController: NavController,

    ) {
    val contentScale = when (contentScaleType) {
        "fitXY" -> ContentScale.FillBounds
        "centerCrop" -> ContentScale.Crop
        "centerInside" -> ContentScale.Inside
        "fitCenter" -> ContentScale.Fit
        else -> ContentScale.Crop // Default
    }
    val modifier = applyModifiers(modifierData)
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
    )
}

@SuppressLint("ModifierFactoryExtensionFunction")
fun applyModifiers(modifierData: ModifierData?): Modifier {
    var modifier = Modifier.fillMaxWidth()
    modifierData?.padding?.let { padding ->
        modifier = modifier.padding(padding.dp)
    }
    modifierData?.height?.let { height ->
        modifier = modifier.height(height.dp)
    }
    modifierData?.width?.let { width ->
        modifier = modifier.width(width.dp)
    }
    modifierData?.fillMaxHeight?.let { fillMaxHeight ->
        Log.d("gfgfgff", "applyModifiers:  height")
        if (fillMaxHeight) {
            modifier = modifier.fillMaxHeight()
        }
    }
    modifierData?.fillMaxWidth?.let { fillMaxWidth ->
        Log.d("gfgfgff", "applyModifiers: width")
        if (fillMaxWidth) {
            modifier = modifier.fillMaxWidth()
        }
    }
    modifierData?.backgroundColor?.let { colorHex ->
        // Parse the hex color string and apply the background color
        val color = Color(android.graphics.Color.parseColor(colorHex))
        modifier = modifier.background(color)
    }
    modifierData?.cornerRadius?.let { radius ->
        // Apply corner radius (clipping the shape)
        modifier = modifier.clip(RoundedCornerShape(radius.dp))
    }
    modifierData?.elevation?.let { elevation ->
        // Apply elevation (shadow effect)
        modifier = modifier.shadow(
            elevation.dp,
            shape = RoundedCornerShape(modifierData.cornerRadius?.dp ?: 0.dp)
        )
    }
    return modifier
}

@Composable
fun DynamicSpacer(
    height: Int,
    navController: NavController,
) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun DynamicDivider(
    modifierData: ModifierData?,
    navController: NavController,
) {
    val modifier = applyModifiers(modifierData).fillMaxWidth()
    Divider(
        modifier = modifier
    )
}

@Composable
fun DynamicBox(
    children: List<UIComponent>, modifierData: ModifierData?,
    navController: NavController,
    mainPresenter: MainPresenter,
) {
    val modifier = applyModifiers(modifierData)
    Box(modifier = modifier) {
        children.forEach { child ->
            DynamicLayout(child, navController, mainPresenter)
        }
    }
}


fun handleAction(action: String, context: Context, mainPresenter: MainPresenter) {
    when (action) {
        "printMessage" -> {
            Toast.makeText(context, "Button clicked!", Toast.LENGTH_SHORT).show()
        }

        "navigateToScreen" -> {
            Log.d("Action", "Navigating to a new screen")
        }

        "showAlert" -> {
            Log.d("Action", "Showing alert dialog")
        }

        "fetchData" -> {
            Log.d("Action", "Fetching data from server")
            mainPresenter.loadUiComponents()
        }

        else -> {
            Log.d("Action", "Unknown action: $action")
        }
    }
}