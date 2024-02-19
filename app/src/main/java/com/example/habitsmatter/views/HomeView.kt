package com.example.habitsmatter.views

import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.habitsmatter.R
import com.example.habitsmatter.data.HabitData
import com.example.habitsmatter.data.HabitViewModel
import com.example.habitsmatter.statics.Constants
import com.example.habitsmatter.statics.Destinations
import com.example.habitsmatter.uiElements.FabView
import com.example.habitsmatter.uiElements.HabitItem
import com.example.habitsmatter.uiElements.TopBarView

@Composable
fun HomeView(
    navController: NavController,
    viewModel: HabitViewModel = HabitViewModel(),
) {
    // Log.d("HomeView_main", "HomeView running")
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .padding(Constants.paddingMedium)
            .fillMaxHeight(),
        containerColor = colorResource(R.color.background_global),
        topBar ={
            TopBarView(title = "All habits", currentScreen = "home", onDeleteClick = {})
        },
        floatingActionButton = {
            FabView(
                onClick = {
                    Log.d("HomeView_FAB", "FAB Pressed")
                    viewModel.addHabit(habitData = HabitData(title = "", content = ""))
                    navController.navigate(Destinations.EDIT_SCREEN + "/0")
                },
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.onPrimary,
                sizeFab = Constants.fabSizeMedium,
                sizeIcon = Constants.fabIconSizeMedium
            )
        }
    ) {
            paddingValues ->
        val habitsList = viewModel.getAllHabits.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxHeight()
        ) {
            items(items = habitsList.value, key = {habit -> habit.id}) {
                    habit ->

                HabitItem(
                    habitData = habit,
                    onItemClick = {
                        val id = habit.id
                        navController.navigate(Destinations.EDIT_SCREEN + "/$id")
                    },
                    onDeleteButtonClick = {viewModel.deleteHabit(habit)}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    HomeView(
        navController = NavController(context = LocalContext.current),
        viewModel = HabitViewModel(),
    )
}