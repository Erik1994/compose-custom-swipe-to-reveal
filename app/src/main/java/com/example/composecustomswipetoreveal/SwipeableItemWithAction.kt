package com.example.composecustomswipetoreveal

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SwipeableItemWithAction(
    modifier: Modifier = Modifier,
    isRevealed: Boolean,
    onExpanded: () -> Unit = {},
    onCollapsed: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit,
    content: @Composable () -> Unit
) {
    var contextMenuWith by remember {
        mutableFloatStateOf(0f)
    }
    val offset = remember {
        Animatable(0f)
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(isRevealed, contextMenuWith) {
        if(isRevealed) {
            offset.animateTo(contextMenuWith)
        } else {
            offset.animateTo(0f)
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier
                .onSizeChanged {
                    contextMenuWith = it.width.toFloat()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions()
        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
//                .graphicsLayer {
//                    translationX = offset.value  --> instead of offset we can use also this
//                }
                .offset { IntOffset(x = offset.value.roundToInt(), 0) }
                .pointerInput(contextMenuWith) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            scope.launch {
                                val newOffset = (offset.value + dragAmount)
                                    .coerceIn(0f, contextMenuWith)
                                offset.snapTo(newOffset)
                            }
                        },
                        onDragEnd = {
                            when {
                                offset.value >= contextMenuWith / 2f -> {
                                    scope.launch { offset.animateTo(contextMenuWith) }
                                    onExpanded()
                                }
                                else -> {
                                    scope.launch { offset.animateTo(0f) }
                                    onCollapsed()
                                }
                            }
                        }
                    )
                }

        ) {
            content()
        }
    }
}