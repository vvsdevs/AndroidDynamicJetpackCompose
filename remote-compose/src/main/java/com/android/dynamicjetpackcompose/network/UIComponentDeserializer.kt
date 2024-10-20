package com.android.dynamicJetpackCompose.network

import com.android.dynamicJetpackCompose.model.ModifierData
import com.android.dynamicJetpackCompose.model.UIComponent
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class UIComponentDeserializer : JsonDeserializer<UIComponent> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): UIComponent {
        val jsonObject = json.asJsonObject
        val type = jsonObject.get("type").asString

        val modifier = jsonObject.get("modifier")?.let {
            context.deserialize<ModifierData>(it, ModifierData::class.java)
        }

        return when (type) {

            "Screen" -> UIComponent.ScreenComponent(
                id = jsonObject.get("id").asString,
                children = context.deserialize(
                    jsonObject.get("children")?.asJsonArray ?: JsonArray(),
                    object : TypeToken<List<UIComponent>>() {}.type
                ),
                modifier = modifier
            )

            // Deserialize Container components with screens
            "Container" -> UIComponent.ContainerComponent(
                screens = context.deserialize(
                    jsonObject.get("screens")?.asJsonArray ?: JsonArray(),
                    object : TypeToken<List<UIComponent.ScreenComponent>>() {}.type
                ),
                children = context.deserialize(
                    jsonObject.get("children")?.asJsonArray ?: JsonArray(),
                    object : TypeToken<List<UIComponent>>() {}.type
                ),
                modifier = modifier
            )


            "Text" -> UIComponent.TextComponent(
                text = jsonObject.get("text")?.asString ?: "",
                color = jsonObject.get("color")?.asString,
                fontSize = jsonObject.get("fontSize")?.asFloat,
                textAlign = jsonObject.get("textAlign")?.asString,
                modifier = modifier
            )

            "Button" -> UIComponent.ButtonComponent(
                text = jsonObject.get("text")?.asString ?: "",
                action = jsonObject.get("action")?.asString ?: "",
                backgroundColor = jsonObject.get("backgroundColor")?.asString,
                textColor = jsonObject.get("textColor")?.asString,
                modifier = modifier
            )

            "Column" -> UIComponent.ColumnComponent(
                children = context.deserialize<List<UIComponent>>(
                    jsonObject.get("children")?.asJsonArray ?: JsonArray(),
                    object : TypeToken<List<UIComponent>>() {}.type
                ),
                modifier = modifier
            )

            "Row" -> UIComponent.RowComponent(
                children = context.deserialize<List<UIComponent>>(
                    jsonObject.get("children")?.asJsonArray ?: JsonArray(),
                    object : TypeToken<List<UIComponent>>() {}.type
                ),
                modifier = modifier
            )

            "LazyColumn" -> UIComponent.LazyColumnComponent(
                children = context.deserialize<List<UIComponent>>(
                    jsonObject.get("children")?.asJsonArray ?: JsonArray(),
                    object : TypeToken<List<UIComponent>>() {}.type
                ),
                modifier = modifier
            )

            "LazyRow" -> UIComponent.LazyRowComponent(
                children = context.deserialize<List<UIComponent>>(
                    jsonObject.get("children")?.asJsonArray ?: JsonArray(),
                    object : TypeToken<List<UIComponent>>() {}.type
                ),
                modifier = modifier
            )

            "ScrollView" -> {
                val children = context.deserialize<List<UIComponent>>(
                    jsonObject.get("children")?.asJsonArray ?: JsonArray(),
                    object : TypeToken<List<UIComponent>>() {}.type
                )
                val screens = context.deserialize<List<UIComponent.ScreenComponent>>(
                    jsonObject.get("screens")?.asJsonArray ?: JsonArray(),
                    object : TypeToken<List<UIComponent.ScreenComponent>>() {}.type
                )
                UIComponent.ScrollViewComponent(
                    children = children,
                    screens = screens,
                    modifier = modifier
                )
            }

            "Card" -> UIComponent.CardComponent(
                content = context.deserialize(
                    jsonObject.get("content"),
                    UIComponent::class.java
                ),
                backgroundColor = jsonObject.get("backgroundColor")?.asString,
                elevation = jsonObject.get("elevation")?.asFloat,
                shape = jsonObject.get("shape")?.asString,
                modifier = modifier,
                navigationTarget = jsonObject.get("navigationTarget")?.asString
            )

            "Image" -> UIComponent.ImageComponent(
                imageUrl = jsonObject.get("imageUrl")?.asString ?: "",
                modifier = modifier
            )

            "Spacer" -> UIComponent.SpacerComponent(
                height = jsonObject.get("height")?.asInt ?: 0,
                modifier = modifier
            )

            "TextField" -> UIComponent.TextFieldComponent(
                hint = jsonObject.get("hint")?.asString ?: "",
                inputType = jsonObject.get("inputType")?.asString ?: "text",
                value = jsonObject.get("value")?.asString ?: "",
                onValueChange = jsonObject.get("onValueChange")?.asString ?: "",
                modifier = modifier
            )

            "Divider" -> UIComponent.DividerComponent(
                modifier = modifier,
                color = jsonObject.get("color")?.asString,
                thickness = jsonObject.get("thickness")?.asFloat
            )

            "Box" -> UIComponent.BoxComponent(
                children = context.deserialize<List<UIComponent>>(
                    jsonObject.get("children")?.asJsonArray ?: JsonArray(),
                    object : TypeToken<List<UIComponent>>() {}.type
                ),
                modifier = modifier
            )

            "Icon" -> UIComponent.IconComponent(
                iconName = jsonObject.get("iconName")?.asString ?: "",
                modifier = modifier
            )

            "Switch" -> UIComponent.SwitchComponent(
                isChecked = jsonObject.get("isChecked")?.asBoolean ?: false,
                onCheckedChangeAction = jsonObject.get("onCheckedChangeAction")?.asString ?: "",
                trackColor = jsonObject.get("trackColor")?.asString,
                thumbColor = jsonObject.get("thumbColor")?.asString,
                modifier = modifier
            )

            "Checkbox" -> UIComponent.CheckboxComponent(
                isChecked = jsonObject.get("isChecked")?.asBoolean ?: false,
                onCheckedChangeAction = jsonObject.get("onCheckedChangeAction")?.asString ?: "",
                color = jsonObject.get("color")?.asString,
                modifier = modifier
            )

            "Slider" -> UIComponent.SliderComponent(
                value = jsonObject.get("value")?.asFloat ?: 0f,
                onValueChangeAction = jsonObject.get("onValueChangeAction")?.asString ?: "",
                min = jsonObject.get("min")?.asFloat ?: 0f,
                max = jsonObject.get("max")?.asFloat ?: 1f,
                trackColor = jsonObject.get("trackColor")?.asString,
                thumbColor = jsonObject.get("thumbColor")?.asString,
                modifier = modifier
            )

            "ProgressBar" -> UIComponent.ProgressBarComponent(
                progress = jsonObject.get("progress")?.asFloat ?: 0f,
                color = jsonObject.get("color")?.asString,
                modifier = modifier
            )

            "FloatingActionButton" -> UIComponent.FloatingActionButtonComponent(
                icon = jsonObject.get("icon")?.asString ?: "",
                action = jsonObject.get("action")?.asString ?: "",
                backgroundColor = jsonObject.get("backgroundColor")?.asString,
                iconTint = jsonObject.get("iconTint")?.asString,
                elevation = jsonObject.get("elevation")?.asFloat,
                shape = jsonObject.get("shape")?.asString,
                modifier = modifier
            )

            else -> throw JsonParseException("Unknown component type: $type")
        }
    }
}