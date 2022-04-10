package com.snakydesign.composetextview.ui

import androidx.compose.ui.text.input.OffsetMapping
import org.junit.Assert.assertEquals
import org.junit.Test

class PriceFilterKtTest {
    @Test
    fun `basic transformation result is correct`() {
        with(priceFilter("")) {
            assertEquals("", text.text)
        }
        with(priceFilter("12")) {
            offsetMapping.assertOriginalToTransform(0, 0)
            offsetMapping.assertTransformedToOriginal(0, 0)
        }
        with(priceFilter("1234")) {
            offsetMapping.assertOriginalToTransform(0, 0)
            offsetMapping.assertTransformedToOriginal(0, 0)
        }
        with(priceFilter("123456789")) {
            offsetMapping.assertOriginalToTransform(0, 0)
            offsetMapping.assertTransformedToOriginal(0, 0)
        }
    }

    @Test
    fun `basic transformation to original is correct`() {
        with(priceFilter("")) {
            assertEquals("", text.text)
        }
        with(priceFilter("12")) {
            offsetMapping.assertOriginalToTransform(0, 0)
            offsetMapping.assertTransformedToOriginal(0, 0)
            offsetMapping.assertOriginalToTransform(1, 1)
            offsetMapping.assertTransformedToOriginal(1, 1)
        }
        with(priceFilter("1234")) {
            offsetMapping.assertOriginalToTransform(0, 0)
            offsetMapping.assertTransformedToOriginal(0, 0)

        }
    }

    @Test
    fun `transformation works okay for one comma`() {

        with(priceFilter("1234")) {
            offsetMapping.assertOriginalToTransform(1, 2)
            offsetMapping.assertTransformedToOriginal(2, 1)
            offsetMapping.assertOriginalToTransform(2, 3)
            offsetMapping.assertTransformedToOriginal(3, 2)
            offsetMapping.assertOriginalToTransform(3, 4)
            offsetMapping.assertTransformedToOriginal(4, 3)
        }
    }

    @Test
    fun `last index is mapped correctly`() {

        with(priceFilter("1234")) {
            offsetMapping.assertOriginalToTransform(4, 5)
            offsetMapping.assertTransformedToOriginal(5, 4)
        }
    }

    @Test
    fun `edge cases mapped correctly`() {

        with(priceFilter("12345678")) { // is mapped to 12,345,678
            offsetMapping.assertOriginalToTransform(2, 3)
            offsetMapping.assertOriginalToTransform(5, 7)


            offsetMapping.assertTransformedToOriginal(3, 2)
            offsetMapping.assertTransformedToOriginal(2, 2)

            offsetMapping.assertTransformedToOriginal(7, 5)
            offsetMapping.assertTransformedToOriginal(6, 5)

        }
    }

    @Test
    fun `empty string is mapped correctly`() {
        with(priceFilter("")) {
            offsetMapping.assertOriginalToTransform(0, 0)
            offsetMapping.assertTransformedToOriginal(0, 0)
        }
    }

    private fun OffsetMapping.assertOriginalToTransform(original: Int, transformed: Int) {
        assertEquals(transformed, originalToTransformed(original))
    }

    private fun OffsetMapping.assertTransformedToOriginal(transformed: Int, original: Int) {
        assertEquals(original, transformedToOriginal(transformed))
    }
}