# Compose Thousands Separator for TextFields

You can find the code and the required tests for implementation of a thousands separator inside a `TextField` in compose.

You can find the main file [here](https://github.com/adibfara/Compose-TextField-Thousands-Separator/blob/main/app/src/main/java/com/snakydesign/composetextview/ui/PriceFilter.kt).

Note: The space for the added thousands separator is accounted for the next number.
So in `1,234`, index `1` and `2` both map to the end of space between 1 and 2.

To be exact, by clicking the comma (as for pointing the cursor), the cursor is always put before the 2 and after the comma (even if behind the comma was selected). This is the same behavior as Compose's own _credit card number_ implementation.

Note: You can have your own implementation of a thousand separator (based on locale or ...) and pass it as `thousandSeparator` argument.