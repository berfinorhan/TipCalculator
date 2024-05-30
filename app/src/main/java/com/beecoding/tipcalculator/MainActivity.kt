package com.beecoding.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beecoding.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {

            }
        }
    }
}

@Composable
fun TipCalculatorApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 36.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = stringResource(R.string.calculate_tip), modifier = Modifier.padding(vertical = 16.dp))

        EditNumberField(modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth())

        Text(
            text = stringResource(R.string.tip_amount, "$0.00"),
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EditNumberField(modifier: Modifier = Modifier) {

    val amountInput = "0"

    TextField(
        value = amountInput,
        onValueChange = {},
        modifier = modifier
    )
}

private fun calculateTip(billAmount: Double, tipPercent: Double = 15.0): String {
    val tip = billAmount * tipPercent / 100
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        TipCalculatorApp()
    }
}