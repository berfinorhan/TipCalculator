package com.beecoding.tipcalculator

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {
    @Test
    fun calculateTip_20PercentNoRoundup() {
        val tipPercent = 20.0
        val amount = 10.0

        val expected = NumberFormat.getCurrencyInstance().format(2)
        val actual = calculateTip(amount, tipPercent, false)

        assertEquals(expected, actual)
    }
}