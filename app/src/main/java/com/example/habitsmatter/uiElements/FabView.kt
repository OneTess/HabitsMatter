package com.example.habitsmatter.uiElements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.habitsmatter.statics.Constants

@Composable
fun FabView(
    onClick: () -> Unit,
    contentColor: Color,
    containerColor: Color,
    sizeFab: Dp,
    sizeIcon: Dp,
) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = Modifier
            .padding(Constants.paddingLarge)
            .size(sizeFab),
        shape = RoundedCornerShape(Constants.roundedCornerDpMedium),
        contentColor = contentColor,
        containerColor = containerColor,
        elevation = FloatingActionButtonDefaults.elevation(Constants.elevationMedium) // This property doesn't seem to change anything
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Habit", modifier = Modifier.size(sizeIcon))
    }
}

@Preview
@Composable
fun FloatingActionButtonViewPreview() {
    FabView(
        onClick = {},
        contentColor = MaterialTheme.colors.primary,
        containerColor = MaterialTheme.colors.onPrimary,
        sizeFab = Constants.fabSizeMedium,
        sizeIcon = Constants.fabIconSizeMedium
    )
}