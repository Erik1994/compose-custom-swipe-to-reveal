package com.example.composecustomswipetoreveal

import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ContactScreen(modifier: Modifier) {
    val context = LocalContext.current
    val contacts = remember {
        mutableStateListOf(
            *(1..100).map {
                ContactUi(
                    id = it,
                    name = "Contact #$it",
                    false
                )
            }.toTypedArray()
        )
    }

    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        itemsIndexed(
            contacts
        ) { index, contact ->
            SwipeableItemWithAction(
                isRevealed = contact.isOptionsRevealed,
                onExpanded = {
                    contacts[index] = contact.copy(isOptionsRevealed = true)
                },
                onCollapsed = {
                    contacts[index] = contact.copy(isOptionsRevealed = false)
                },
                actions = {
                    ActionIcon(
                        onIconClick = {
                            contacts.remove(contact)
                            Toast.makeText(
                                context,
                                "Contact ${contact.name} is deleted",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        backgroundColor = Color.Red,
                        icon = Icons.Default.Delete,
                        modifier = Modifier.fillMaxHeight()
                    )
                    ActionIcon(
                        onIconClick = {
                            contacts[index] = contact.copy(isOptionsRevealed = false)
                            Toast.makeText(
                                context,
                                "Contact ${contact.name} is sent in email",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        backgroundColor = Color.Yellow,
                        icon = Icons.Default.Email,
                        modifier = Modifier.fillMaxHeight()
                    )
                    ActionIcon(
                        onIconClick = {
                            contacts[index] = contact.copy(isOptionsRevealed = false)
                            Toast.makeText(
                                context,
                                "Contact ${contact.name} is shared",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        backgroundColor = Color.Magenta,
                        icon = Icons.Default.Share,
                        modifier = Modifier.fillMaxHeight()
                    )
                },
                content = {
                    Text(
                        text = "Contact ${contact.id}",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            )
        }
    }
}