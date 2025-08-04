//package com.example.italigo.ui.view
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Switch
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
//import com.example.italigo.ui.viewmodels.SettingsViewModel
//
//@Composable
//fun SettingsScreen(
//    navController: NavHostController,
//    viewModel: SettingsViewModel = hiltViewModel()
//                   ) {
//    // Collect the current dark mode setting from the SettingsViewModel
//    val isDarkMode by viewModel.isDarkMode.collectAsState(initial = false)
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.Start
//    ) {
//        Text("Settings", style = MaterialTheme.typography.headlineLarge)
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Dark Mode Toggle
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text("Enable Dark Mode", modifier = Modifier.weight(1f))
//            Switch(
//                checked = isDarkMode,
//                onCheckedChange = { newIsDarkMode ->
//                    // Launch a coroutine to call the suspend function
//                    LaunchedEffect(newIsDarkMode) {
//                        viewModel.setDarkMode(newIsDarkMode)
//                    }
//                }
//            )
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun PreviewSettingsScreen() {
//    val settingsViewModel: SettingsViewModel = viewModel() // Getting the ViewModel
//    SettingsScreen(navController = NavHostController(context = null), viewModel = settingsViewModel) // Pass ViewModel to SettingsScreen
//}
