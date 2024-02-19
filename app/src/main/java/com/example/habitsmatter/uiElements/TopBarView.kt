package com.example.habitsmatter.uiElements

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.habitsmatter.R
import com.example.habitsmatter.statics.Constants

@Composable
fun TopBarView(
    title: String,
    currentScreen: String,
    onBackNavClicked:() -> Unit = {},
    onActionClicked: @Composable RowScope.() -> Unit = {},
    onDeleteClick:() -> Unit
) {
    val navigationIconComposable : (@Composable () -> Unit)? = {
        IconButton(onClick = { onBackNavClicked() }) {
            if (!currentScreen.contains("home")){
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back button",
                    tint = colorResource(id = R.color.text_primary)
                )
            } else {
                // TODO: Come up with some other button in place of the back arrow when it's not displayed
                null
            }
        }
    }
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = Constants.fontSizeMediumPlus,
                modifier = Modifier
                    .padding(horizontal = Constants.paddingSmall)
                    .heightIn(max = 24.dp),
                color = colorResource(id = R.color.text_primary),
                overflow = TextOverflow.Ellipsis
            )
        },
        elevation = Constants.elevationMedium,
        contentColor = colorResource(id = R.color.white),
        backgroundColor = colorResource(id = R.color.background_primary),
        modifier = Modifier
            .padding(top = Constants.paddingMedium, bottom = Constants.paddingMedium)
            .clip(RoundedCornerShape(percent = 100)), // Rounded corners for anything basically
        navigationIcon = navigationIconComposable,
        // TODO: Make choice of buttons dependent on the current screen
        actions = {
            var expanded by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100)) // This doesn't do much: cause is seemingly the fact that the Box is too small to display proper click animation
                    // .size(40.dp) // This doesn't do much: cause is seemingly the fact that the Box is too small to display proper click animation
                    .padding(
                        top = Constants.paddingMedium,
                        bottom = Constants.paddingMedium,
                        end = Constants.paddingMediumPlus
                    )
                    .clickable { expanded = !expanded }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Dropdown Menu",
                    tint = colorResource(id = R.color.text_primary)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    // Item Delete
                    DropdownMenuItem(
                        text = { Text(text = "Delete") },
                        onClick = {
                            Log.d("EditView_DropdownMenu", "Pressed ItemDelete")
                            onDeleteClick()
                        }
                    )
                    // TODO: Item 2
                    DropdownMenuItem(
                        text = { Text(text = "Dropdown Item 2") },
                        onClick = {
                            /* TODO */
                        }
                    )
                    // TODO: Item 3
                    DropdownMenuItem(
                        text = { Text(text = "Dropdown Item 2") },
                        onClick = {
                            /* TODO */
                        }
                    )
                }
            }
        }
    )
}
