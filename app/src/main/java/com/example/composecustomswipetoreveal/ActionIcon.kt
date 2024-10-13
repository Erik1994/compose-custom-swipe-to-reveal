package com.example.composecustomswipetoreveal

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ActionIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    backgroundColor: Color,
    tint: Color = Color.White,
    contentDescription: String? = null,
    onIconClick: () -> Unit,
) {
    IconButton(
        onClick = onIconClick,
        modifier = modifier
            .background(color = backgroundColor)
    ) {
        Icon(
            imageVector = icon,
            tint = tint,
            contentDescription = contentDescription
        )
    }
}