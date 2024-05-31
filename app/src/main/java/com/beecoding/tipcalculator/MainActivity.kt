package com.beecoding.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beecoding.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                TipCalculatorApp()
            }
        }
    }
}

@Composable
fun TipCalculatorApp() {

    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0

    var tipPercentageInput by remember { mutableStateOf("15") }
    val tipPercentage = tipPercentageInput.toDoubleOrNull() ?: 15.0

    var roundUp by remember { mutableStateOf(false) }

    val tip = calculateTip(amount, tipPercentage, roundUp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 36.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        //Compose keeps track of each composable that reads state value properties
        // and triggers a recomposition when its value changes.
        EditNumberField(
            value = amountInput,
            onValueChange = { newValue -> amountInput = newValue },
            labelResId = R.string.bill_amount,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        EditNumberField(
            value = tipPercentageInput,
            onValueChange = { newValue -> tipPercentageInput = newValue },
            labelResId = R.string.tip_percentage,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        RoundTheTipSwitchRow(
            isChecked = roundUp,
            onChecked = { newValue -> roundUp = newValue }
        )

        Text(
            text = stringResource(R.string.tip_amount, tip),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
fun RoundTheTipSwitchRow(
    isChecked: Boolean,
    onChecked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip))
        Switch(
            checked = isChecked,
            onCheckedChange = onChecked
        )
    }
}

@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelResId: Int,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(text = stringResource(labelResId)) },
        singleLine = true,
        keyboardOptions = keyboardOptions
    )
}

private fun calculateTip(
    billAmount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean
): String {
    val tip = if (roundUp) {
        ceil(billAmount * tipPercent / 100)
    } else {
        billAmount * tipPercent / 100
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        TipCalculatorApp()
    }
}