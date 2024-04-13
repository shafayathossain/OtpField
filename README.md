# OTP Field Component for Jetpack Compose

The OTP Field component is a flexible and customizable one-time password input solution designed specifically for Android apps using Jetpack Compose. It supports various customizations, enabling developers to tailor the appearance and functionality to match different application needs effectively.

<p align="center">
  <img src="readmeassets/recording.gif?raw=true" alt="Demo">
</p>


## Features

- **Customizable Number of Fields**: Configure any number of OTP boxes.
- **Visual Customization**: Extensive support for modifiers allows for complete control over the appearance of each OTP box.
- **Automatic Focus Management**: Focus automatically transitions from one field to the next to enhance user experience.
- **Secure Input**: Supports masking for sensitive input, making it suitable for PINs or password entries.
- **Adaptive Layout**: Automatically adjusts the size and arrangement of OTP boxes based on the device's screen width.

## Installation
Include the OTP Field component into your Jetpack Compose setup by integrating the provided code into your Compose UI project. Ensure you have the latest version of Compose in your project.

## Usage

### Basic Setup

To integrate the OTP Field component, instantiate it within your Compose layout by setting up the necessary state and configuration:

```kotlin
@Composable
fun ExampleOtpScreen() {
    val otpValue = remember { mutableStateOf("") }

    OtpInputField(
        otp = otpValue,
        count = 5,
        otpBoxModifier = Modifier.border(1.dp, Color.Black).background(Color.White),
        otpTextType = KeyboardType.Number
    )
}
```

![Basic Setup](readmeassets/basic_setup.png?raw=true "Basic Setup")

### Secure Input Example

Enable character masking for enhanced security, suitable for PINs or passwords:

```kotlin
OtpInputField(
    otp = remember { mutableStateOf("") },
    count = 5,
    otpTextType = KeyboardType.NumberPassword,
    otpBoxModifier = Modifier
        .border(1.dp, Color.Gray)
        .background(Color.White)
        .padding(4.dp)  // Padding inside OTP boxes should be handled carefully
)
```
![Secure Input](readmeassets/secure_input.png?raw=true "Secure Setup")

### Design Examples

#### Boxy Design

For a boxy design where each OTP character box appears as a distinct element:

```kotlin
OtpInputField(
    otp = remember { mutableStateOf("") },
    count = 5,
    textColor = Color.White,
    otpBoxModifier = Modifier
        .size(50.dp)
        .border(2.dp, Color(0xFF277F51), shape = RoundedCornerShape(4.dp))
)
```
![Boxy Design](readmeassets/boxy_otp_field.png?raw=true "Boxy Design")

#### Underline Design

For a minimalist design featuring only an underline for each OTP box:

```kotlin
OtpInputField(
    otp = remember { mutableStateOf("") },
    count = 5,
    otpBoxModifier = Modifier
        .bottomStroke(color = Color.DarkGray, strokeWidth = 2.dp)
)
```
![Underline Design](readmeassets/underline_otp_field.png?raw=true "Underline Design")

### Parameter Descriptions

- **otp**: A `MutableState<String>` that holds the entire OTP value. This is a reactive state that triggers UI updates when the OTP value changes, ensuring that the UI is always in sync with the latest value.

- **count**: An `Int` that specifies the number of OTP boxes to be displayed. This allows customization of the OTP field length according to specific requirements, such as different OTP lengths for different authentication processes.

- **otpBoxModifier**: A `Modifier` applied to each OTP box for styling. This parameter allows developers to apply custom styles such as borders, backgrounds, sizes, and shapes to individual OTP boxes, enhancing the flexibility and aesthetic appeal of the OTP input field.

- **otpTextType**: A `KeyboardType` that specifies the type of keyboard to show when a field is focused. This can be set to `KeyboardType.Number` for numeric OTPs or `KeyboardType.Text` for alphanumeric OTPs, depending on the security requirements of the application.

- **textColor**: A `Color` used to set the text color within each OTP box. This parameter provides the ability to customize the color of the text inside the OTP boxes, allowing for better integration with the overall design theme of the application.



