package com.example.habitsmatter.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import com.example.habitsmatter.R
import com.example.habitsmatter.data.HabitData
import com.example.habitsmatter.data.HabitViewModel
import com.example.habitsmatter.methods.autoSave
import com.example.habitsmatter.statics.Constants
import com.example.habitsmatter.uiElements.TopBarView
import com.example.habitsmatter.uiElements.CustomTextField
import com.example.habitsmatter.uiElements.RadioButtons

@Composable
fun EditView(
    id: Int,
    viewModel: HabitViewModel = HabitViewModel(),
    navController: NavController
) {
    // Log.d("EditView_main", "EditView running")
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if (id != 0) {
        val habit = viewModel.getHabit(id).collectAsState(initial = HabitData(0, "", ""))
        viewModel.onHabitTitleChanged(habit.value.title)
        viewModel.onHabitContentsChanged(habit.value.content)
    } else {
        viewModel.onHabitTitleChanged("")
        viewModel.onHabitContentsChanged("")
    }

    Scaffold(
        modifier = Modifier.padding(Constants.paddingMedium),
        containerColor = colorResource(R.color.background_global),
        topBar = {
            TopBarView(title =
            if (id != 0) {
                "Editing ${viewModel.habitTitleState.value}"
            } else {
                "Creating ${viewModel.habitTitleState.value}" // TODO: This stopped working. I'm guessing the way Autosave works caused it to break. Cuz on the editing screen it displays correctly.
            },
                currentScreen = "editing",
                onBackNavClicked = { navController.navigateUp() },
                onDeleteClick = {
                    // TODO: Doesn't work when creating a new habit. On delete press it just exits to the HomeView autosaving the new habit
                    val habitId = id
                    navController.navigateUp()
                    viewModel.editHabit(habit = HabitData(id = habitId, title = "", content = ""))
                }
            )
        }
    ) {
            paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    start = paddingValues.calculateLeftPadding(LayoutDirection.Ltr),
                    end = paddingValues.calculateRightPadding(LayoutDirection.Rtl),
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val title by viewModel.habitTitleState.collectAsState()
            val content by viewModel.habitContentState.collectAsState()
            val type by viewModel.habitTypeState.collectAsState()

            // Buttons choosing Habit type
            if (id == 0) {
                RadioButtons(viewModel, onTypeChange = {
                    viewModel.onHabitTypeChanged(it)
                    autoSave(id, viewModel)
                })
            }

            Row {
                // Testing Text Field
                Text(text = when (type) {
                    "binary" -> {"B"}
                    "enumerable" -> {"N"}
                    else -> {"E!"}
                }
                )
                // Title Text Field
                CustomTextField(
                    value = title,
                    fontSize = Constants.fontSizeExtraLarge,
                    fontWeight = FontWeight.Black,
                    onValueChange = {
                        viewModel.onHabitTitleChanged(it)
                        autoSave(id, viewModel)
                    },
                    placeholder = "Title"
                )
            }

            // Content Text Field
            CustomTextField(
                value = content,
                fontSize = Constants.fontSizeMedium,
                fontWeight = FontWeight.Normal,
                onValueChange = {
                    viewModel.onHabitContentsChanged(it)
                    autoSave(id, viewModel)
                },
                placeholder = "Important comments"
            )
        }
    }
}
