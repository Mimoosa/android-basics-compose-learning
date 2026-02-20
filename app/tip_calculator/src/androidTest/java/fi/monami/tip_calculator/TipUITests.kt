package fi.monami.tip_calculator

import androidx.compose.ui.test.junit4.createComposeRule
import fi.monami.tip_calculator.ui.theme.AndroidoStudioPracticeTheme
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import java.text.NumberFormat

class TipUITests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            AndroidoStudioPracticeTheme{
                TipTimeLayout()
            }
        }
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with this text was found.")
    }
}