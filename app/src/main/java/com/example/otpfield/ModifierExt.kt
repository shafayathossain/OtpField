package com.example.otpfield

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Creates a Modifier that adds a bottom stroke to a Composable.
 *
 * @param color The color of the stroke.
 * @param strokeWidth The thickness of the stroke.
 * @return A Modifier that draws a bottom stroke.
 */
fun Modifier.bottomStroke(color: Color, strokeWidth: Dp = 2.dp): Modifier = this.then(
    Modifier.drawBehind {
        val strokePx = strokeWidth.toPx()
        // Draw a line at the bottom
        drawLine(
            color = color,
            start = Offset(x = 0f, y = size.height - strokePx / 2),
            end = Offset(x = size.width, y = size.height - strokePx / 2),
            strokeWidth = strokePx
        )
    }
)