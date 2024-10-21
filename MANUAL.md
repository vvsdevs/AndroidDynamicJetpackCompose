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

### Text
```json
{
    "type": "Text",
    "text": "Hello World",
    "modifier": {
        "padding": 16,
        "backgroundColor": "#FFFFFF"
    },
    "color": "#000000",
    "fontSize": 16,
    "textAlign": "center"
}
```
### ButtonComponent
A customizable button.

- **text**: The text on the button.
- **action**: The action triggered when the button is clicked.
- **modifier**: Optional `ModifierData`.
- **backgroundColor**: Background color of the button.
- **textColor**: Text color.
- **elevation**: Elevation of the button.
```json
{
    "type": "Button",
    "text": "Click Me",
    "modifier": {
        "padding": 12,
        "backgroundColor": "#FF5733"
    },
    "textColor": "#FFFFFF",
    "elevation": 4,
    "action": "navigateToScreen2"
}
```

### ImageComponent
Displays an image from a URL.

- **imageUrl**: URL of the image.
- **modifier**: Optional `ModifierData`.
- **contentDescription**: Description for accessibility.
- **contentScale**: How the image is scaled (e.g., fitXY, centerCrop).
```json
{
    "type": "Image",
    "imageUrl": "https://example.com/image.png",
    "modifier": {
        "width": 200,
        "height": 150,
        "contentScale": "fitCenter",
        "padding": 8
    },
    "contentDescription": "Sample Image"
}
```

### CardComponent
A card that can contain other components.

- **content**: The child component within the card.
- **modifier**: Optional `ModifierData`.
- **backgroundColor**: Background color of the card.
- **elevation**: Elevation/shadow depth.
- **borderColor**: Border color (optional).
- **navigationTarget**: Target screen to navigate when clicked.
```json
{
    "type": "Card",
    "modifier": {
        "padding": 16,
        "width": 300,
        "cornerRadius": 8,
        "elevation": 2
    },
    "backgroundColor": "#FFFFFF",
    "content": {
        "type": "Column",
        "children": [
            {
                "type": "Text",
                "text": "Card Title",
                "color": "#000000",
                "fontSize": 16,
                "textAlign": "start"
            },
            {
                "type": "Text",
                "text": "This is some card content.",
                "color": "#333333",
                "fontSize": 14,
                "textAlign": "start"
            }
        ]
    }
}
```

### ColumnComponent
Organizes children vertically.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.
- **verticalArrangement**: Spacing arrangement.
- **horizontalAlignment**: Alignment of children.
```json
{
    "type": "Column",
    "modifier": {
        "padding": 8
    },
    "children": [
        {
            "type": "Text",
            "text": "Column Item 1",
            "color": "#000000",
            "fontSize": 14
        },
        {
            "type": "Button",
            "text": "Action",
            "textColor": "#FFFFFF",
            "modifier": {
                "backgroundColor": "#007BFF",
                "padding": 8
            },
            "action": "performAction"
        }
    ]
}
```
### RowComponent
Organizes children horizontally.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.
- **horizontalArrangement**: Spacing arrangement.
- **verticalAlignment**: Alignment of children.
```json
{
    "type": "Row",
    "modifier": {
        "padding": 8
    },
    "children": [
        {
            "type": "Image",
            "imageUrl": "https://example.com/icon.png",
            "modifier": {
                "width": 50,
                "height": 50
            }
        },
        {
            "type": "Text",
            "text": "Row Item",
            "color": "#000000",
            "fontSize": 14,
            "modifier": {
                "padding": 8
            }
        }
    ]
}
```
### LazyColumnComponent
A scrollable column for large data sets.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.
```json
{
    "type": "LazyColumn",
    "modifier": {
        "padding": 8
    },
    "children": [
        {
            "type": "Text",
            "text": "Item 1",
            "color": "#000000",
            "fontSize": 14
        },
        {
            "type": "Text",
            "text": "Item 2",
            "color": "#000000",
            "fontSize": 14
        }
    ]
}
```
### LazyRowComponent
A scrollable row for large data sets.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.
```json
{
    "type": "LazyRow",
    "modifier": {
        "padding": 8,
        "height": 100
    },
    "children": [
        {
            "type": "Card",
            "modifier": {
                "width": 100,
                "height": 100,
                "padding": 8,
                "cornerRadius": 8
            },
            "content": {
                "type": "Text",
                "text": "Row Item",
                "color": "#000000",
                "fontSize": 12,
                "textAlign": "center"
            }
        }
    ]
}
```
### ScrollViewComponent
A scrollable view that contains multiple components.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.
```json
{
    "type": "ScrollView",
    "modifier": {
        "padding": 16,
        "backgroundColor": "#F8F8F8"
    },
    "children": [
        {
            "type": "Text",
            "text": "Scrollable Content",
            "color": "#333333",
            "fontSize": 16
        }
    ]
}
```
### SpacerComponent
Adds space between components.

- **height**: Height of the spacer.
- **modifier**: Optional `ModifierData`.
```json
{
    "type": "Spacer",
    "modifier": {
        "height": 16
    }
}
```
### BoxComponent
A flexible container that can hold components.

- **children**: List of `UIComponent` objects.
- **modifier**: Optional `ModifierData`.
- **contentAlignment**: Alignment of content inside the box.
```json
{
    "type": "Box",
    "modifier": {
        "padding": 8,
        "backgroundColor": "#EFEFEF"
    },
    "children": [
        {
            "type": "Text",
            "text": "Box Content",
            "color": "#000000",
            "fontSize": 14
        }
    ]
}
```
### TextFieldComponent
An editable text field.

- **hint**: Placeholder text.
- **inputType**: Type of input (e.g., text, number).
- **modifier**: Optional `ModifierData`.
- **value**: Current text value.
- **onValueChange**: Action triggered when text changes.
```json
{
    "type": "TextField",
    "hint": "Enter your name",
    "value": "",
    "modifier": {
        "padding": 8,
        "backgroundColor": "#F0F0F0"
    },
    "textColor": "#000000",
    "backgroundColor": "#FFFFFF",
    "isSingleLine": true,
    "maxLines": 1
}
```

### DividerComponent
Adds a divider line.

- **modifier**: Optional `ModifierData`.
- **color**: Color of the divider.
- **thickness**: Thickness of the line.
```json
{
    "type": "Divider",
    "modifier": {
        "padding": 8
    },
    "color": "#CCCCCC",
    "thickness": 1
}
```
### IconComponent
Displays an icon.

- **iconName**: Name or identifier of the icon.
- **modifier**: Optional `ModifierData`.
```json
{
    "type": "Icon",
    "iconName": "favorite",
    "modifier": {
        "padding": 8,
        "size": 24
    },
    "tintColor": "#FF0000"
}
```
### SwitchComponent
A switch that toggles between two states.

- **isChecked**: Initial state of the switch.
- **onCheckedChangeAction**: Action when the state changes.
- **modifier**: Optional `ModifierData`.
```json
{
    "type": "Switch",
    "isChecked": true,
    "modifier": {
        "padding": 8
    },
    "trackColor": "#4CAF50",
    "thumbColor": "#FFFFFF",
    "onCheckedChangeAction": "toggleSetting"
}
```
### CheckboxComponent
A checkbox for selection.

- **isChecked**: Initial state of the checkbox.
- **onCheckedChangeAction**: Action when the state changes.
- **modifier**: Optional `ModifierData`.
```json
{
    "type": "Checkbox",
    "isChecked": false,
    "modifier": {
        "padding": 8
    },
    "color": "#2196F3",
    "onCheckedChangeAction": "updatePreference"
}
```
### SliderComponent
A slider for selecting a value within a range.

- **value**: Initial value of the slider.
- **onValueChangeAction**: Action when the value changes.
- **modifier**: Optional `ModifierData`.
- **min**: Minimum value.
- **max**: Maximum value.
```json
{
    "type": "Slider",
    "value": 0.5,
    "modifier": {
        "padding": 8
    },
    "trackColor": "#3F51B5",
    "thumbColor": "#FFFFFF",
    "min": 0,
    "max": 1,
    "onValueChangeAction": "updateValue"
}
```
### ProgressBarComponent
Displays a progress bar.

- **progress**: Current progress value.
- **modifier**: Optional `ModifierData`.
- **color**: Color of the progress bar.
```json
{
    "type": "ProgressBar",
    "progress": 0.7,
    "modifier": {
        "padding": 8
    },
    "color": "#FF9800"
}
```
### FloatingActionButtonComponent
A floating button for actions.

- **icon**: Icon for the button.
- **action**: Action when the button is clicked.
- **modifier**: Optional `ModifierData`.
- **backgroundColor**: Background color.
- **elevation**: Elevation of the button.
```json
{
    "type": "FloatingActionButton",
    "icon": "add",
    "modifier": {
        "padding": 16,
        "backgroundColor": "#FF5722"
    },
    "iconTint": "#FFFFFF",
    "elevation": 6,
    "action": "openDialog"
}
```
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