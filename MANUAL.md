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