package com.android.dynamicjetpackcompose.model

sealed class UIComponent {


    // New Container Component to hold multiple components
    data class ContainerComponent(
        val screens: List<ScreenComponent>,
        val children: List<UIComponent> = emptyList(),
        val modifier: ModifierData? = null
    ) : UIComponent()

    // Screen Component to hold the screen's content
    data class ScreenComponent(
        val id: String,
        val children: List<UIComponent> = emptyList(),
        val modifier: ModifierData? = null
    ) : UIComponent()

    data class TextComponent(
        val text: String = "",
        val modifier: ModifierData? = null,
        val color: String? = null,
        val fontSize: Float? = null,
        val fontStyle: String? = null,
        val fontWeight: Int? = null,
        val fontFamily: String? = null,
        val letterSpacing: Float? = null,
        val textDecoration: String? = null,
        val textAlign: String? = null,
        val lineHeight: Float? = null,
        val overflow: String? = null,
        val softWrap: Boolean = true,
        val maxLines: Int = Int.MAX_VALUE,
        val minLines: Int = 1
    ) : UIComponent()

    data class ButtonComponent(
        val text: String = "",
        val action: String = "",
        val modifier: ModifierData? = null,
        val backgroundColor: String? = null,
        val textColor: String? = null,
        val elevation: Float? = null,
        val shape: String? = null
    ) : UIComponent()

    data class ImageComponent(
        val imageUrl: String = "",
        val modifier: ModifierData? = null,
        val contentDescription: String? = null,
        val contentScale: String? = null,
        val alpha: Float = 1f
    ) : UIComponent()

    data class CardComponent(
        val content: UIComponent? = null,
        val modifier: ModifierData? = null,
        val backgroundColor: String? = null,
        val elevation: Float? = null,
        val shape: String? = null,
        val borderColor: String? = null,
        val borderWidth: Float? = null,
        val navigationTarget: String? = null
    ) : UIComponent()

    data class ColumnComponent(
        val children: List<UIComponent> = emptyList(),
        val modifier: ModifierData? = null,
        val verticalArrangement: String? = null,
        val horizontalAlignment: String? = null
    ) : UIComponent()

    data class RowComponent(
        val children: List<UIComponent> = emptyList(),
        val modifier: ModifierData? = null,
        val horizontalArrangement: String? = null,
        val verticalAlignment: String? = null
    ) : UIComponent()

    data class LazyColumnComponent(
        val children: List<UIComponent> = emptyList(),
        val modifier: ModifierData? = null,
        val verticalArrangement: String? = null,
        val horizontalAlignment: String? = null
    ) : UIComponent()

    data class LazyRowComponent(
        val children: List<UIComponent> = emptyList(),
        val modifier: ModifierData? = null,
        val horizontalArrangement: String? = null,
        val verticalAlignment: String? = null
    ) : UIComponent()

    data class ScrollViewComponent(
        val children: List<UIComponent> = emptyList(),
        val modifier: ModifierData? = null,
        val screens: List<ScreenComponent> = emptyList(),
    ) : UIComponent()

    data class SpacerComponent(
        val height: Int = 0,
        val modifier: ModifierData? = null
    ) : UIComponent()

    data class BoxComponent(
        val children: List<UIComponent> = emptyList(),
        val modifier: ModifierData? = null,
        val contentAlignment: String? = null
    ) : UIComponent()

    data class TextFieldComponent(
        val hint: String = "",
        val inputType: String = "text",
        val modifier: ModifierData? = null,
        val value: String = "",
        val onValueChange: String = "",
        val textColor: String? = null,
        val backgroundColor: String? = null,
        val isSingleLine: Boolean = true,
        val maxLines: Int = Int.MAX_VALUE
    ) : UIComponent()

    data class DividerComponent(
        val modifier: ModifierData? = null,
        val color: String? = null,
        val thickness: Float? = null
    ) : UIComponent()

    data class IconComponent(
        val iconName: String = "",
        val modifier: ModifierData? = null,
        val tintColor: String? = null,
        val size: Float? = null
    ) : UIComponent()

    data class SwitchComponent(
        val isChecked: Boolean = false,
        val onCheckedChangeAction: String = "",
        val modifier: ModifierData? = null,
        val trackColor: String? = null,
        val thumbColor: String? = null
    ) : UIComponent()

    data class CheckboxComponent(
        val isChecked: Boolean = false,
        val onCheckedChangeAction: String = "",
        val modifier: ModifierData? = null,
        val color: String? = null
    ) : UIComponent()

    data class SliderComponent(
        val value: Float = 0f,
        val onValueChangeAction: String = "",
        val modifier: ModifierData? = null,
        val trackColor: String? = null,
        val thumbColor: String? = null,
        val min: Float = 0f,
        val max: Float = 1f
    ) : UIComponent()

    data class ProgressBarComponent(
        val progress: Float = 0f,
        val modifier: ModifierData? = null,
        val color: String? = null
    ) : UIComponent()

    data class FloatingActionButtonComponent(
        val icon: String = "",
        val action: String = "",
        val modifier: ModifierData? = null,
        val backgroundColor: String? = null,
        val iconTint: String? = null,
        val elevation: Float? = null,
        val shape: String? = null
    ) : UIComponent()
}