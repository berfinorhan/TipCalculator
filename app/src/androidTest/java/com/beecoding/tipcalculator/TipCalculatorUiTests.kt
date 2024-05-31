package com.beecoding.tipcalculator

import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.runner.RunWith

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.beecoding.tipcalculator.ui.theme.TipCalculatorTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

@RunWith(AndroidJUnit4::class)
class TipCalculatorUiTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipCalculatorApp()
            }
        }
        composeTestRule.onNodeWithText("Bill amount").performTextInput("10")
        composeTestRule.onNodeWithText("Tip percentage").performTextClearance()
        composeTestRule.onNodeWithText("Tip percentage").performTextInput("20")

        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found.")
    }
}