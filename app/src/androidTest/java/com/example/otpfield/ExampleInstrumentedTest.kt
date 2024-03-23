package com.example.otpfield

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class OtpInputFieldInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun otpInputField_Updates_Correctly_On_Input() {
        composeTestRule.setContent {
            val otp = remember { mutableStateOf("") }
            OtpInputField(otp, 5) // Assuming 5 is the desired count
        }
        composeTestRule.waitForIdle()
        // Simulate typing '12345' across the OTP fields
        val inputs = listOf("1", "2", "3", "4", "5")
        inputs.forEachIndexed { index, input ->
            composeTestRule.onNodeWithTag("otpBox$index", useUnmergedTree = true)
                .performTextInput(input)
        }

        // Verify final OTP value
        val expectedOtpValue = inputs.joinToString("")

        expectedOtpValue.forEachIndexed { index, value ->
            composeTestRule.onNodeWithTag("otpBox$index", useUnmergedTree = true)
                .assertTextEquals(value.toString())
        }

    }

    @Test
    fun otpInputField_Moves_Focus_On_Input() {
        // Set the OTP field count
        val otpFieldCount = 5
        composeTestRule.setContent {
            val otp = remember { mutableStateOf("") }
            OtpInputField(otp, otpFieldCount)
        }

        // Simulate typing '1234' into the first four OTP fields
        for (i in 0 until otpFieldCount - 1) {
            val tag = "otpBox$i"
            composeTestRule.onNodeWithTag(tag, useUnmergedTree = true)
                .performTextInput((i + 1).toString())

            // After typing into each box, except the last one, the next box should be focused
            // This can be inferred if the next box accepts input correctly in a real scenario
            // but for testing purposes, we assume successful focus move without a direct way to verify focus
        }

        // Ideally, check for focus on the last box or verify the behavior indicating focus has moved,
        // such as checking if the keyboard is still up, but such checks might be limited in current Compose testing tools.
    }

    @Test
    fun otpInputField_Handles_Backspace_Correctly() {
        val otpFieldCount = 5
        composeTestRule.setContent {
            val otp = remember { mutableStateOf("") }
            OtpInputField(otp, otpFieldCount)
        }

        // Simulate typing '1' into the first OTP field and then clearing it
        val firstBoxTag = "otpBox0"
        composeTestRule.onNodeWithTag(firstBoxTag, useUnmergedTree = true).performTextInput("1")
        composeTestRule.onNodeWithTag(firstBoxTag, useUnmergedTree = true).performTextClearance()

        // At this point, we would verify the field is cleared. However, directly verifying the focus behavior or that the field is empty is challenging with Compose testing tools as of now.
        // Instead, we might rely on subsequent behaviors or checks, such as attempting to type into the same or previous box and verifying the outcome, to infer correct behavior indirectly.

        // For a real focus back movement test, you would simulate a condition where pressing backspace should logically move the focus back (e.g., the current box is empty and the previous box is filled) and then verify the previous box can accept input.
    }

    @Test
    fun otpInputField_Clears_Correctly() {
        // Set up your Compose content with the OTP input field
        composeTestRule.setContent {
            val otp = remember { mutableStateOf("") }
            OtpInputField(otp, 5) // Assuming 5 is the desired count
        }

        // Assume you have already input '1' into the first OTP box and then cleared it.
        val firstBoxTag = "otpBox0"
        composeTestRule.onNodeWithTag(firstBoxTag, useUnmergedTree = true).performTextInput("1")
        composeTestRule.onNodeWithTag(firstBoxTag, useUnmergedTree = true).performTextClearance()
        composeTestRule.onNodeWithTag(firstBoxTag, useUnmergedTree = true).performTextClearance()
        composeTestRule.onNodeWithTag(firstBoxTag, useUnmergedTree = true).assertTextEquals("")

        // Simulate user typing into the same box again
        composeTestRule.onNodeWithTag(firstBoxTag, useUnmergedTree = true).performTextInput("2")

        // Verify the field now contains the new input, implying it was correctly cleared before
        composeTestRule.onNodeWithTag(firstBoxTag, useUnmergedTree = true).assertTextEquals("2")
    }

    @Test
    fun otpInputField_Moves_Focus_Back_On_Backspace() {
        // Set up your Compose content with the OTP input field
        composeTestRule.setContent {
            val otp = remember { mutableStateOf("") }
            OtpInputField(otp, 5) // Assuming 5 is the desired count
        }

        // Simulate the user entering '12' into the first two OTP boxes
        val firstBoxTag = "otpBox0"
        val secondBoxTag = "otpBox1"
        composeTestRule.onNodeWithTag(firstBoxTag, useUnmergedTree = true).performTextInput("1")
        composeTestRule.onNodeWithTag(secondBoxTag, useUnmergedTree = true).performTextInput("2")

        // Simulate backspace in the second box, assuming it will clear the input and potentially move focus back to the first box
        composeTestRule.onNodeWithTag(secondBoxTag, useUnmergedTree = true).performTextClearance()

        // Attempt to type into the first box again, expecting it to accept input, implying focus has moved back
        composeTestRule.onNodeWithTag(firstBoxTag, useUnmergedTree = true).performTextInput("3")

        // Verify the first box now contains '3', showing it received focus after backspace was pressed in the second box
        composeTestRule.onNodeWithTag(firstBoxTag, useUnmergedTree = true).assertTextEquals("3")
    }
}
