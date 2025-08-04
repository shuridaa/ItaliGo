package com.example.italigo.ui.view

import com.example.italigo.data.lesson2Questions

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.draw.shadow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lesson2ResultScreen(navController: NavController, score: Int) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lesson Complete",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White // White text for contrast
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF008C45) // Green color for the TopAppBar
                ),
                modifier = Modifier.shadow(4.dp) // Adding shadow to the TopAppBar
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your Score: $score/${lesson2Questions.size}",
                    style = TextStyle(
                        fontSize = 24.sp, // Increased font size
                        color = MaterialTheme.colorScheme.onBackground // Text color
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = { navController.navigate("home") },
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width(200.dp), // Set fixed width for the button
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF006400), // Dark green color for button
                        contentColor = Color.White // White text color for contrast
                    ),
                    shape = MaterialTheme.shapes.medium // Rounded corners
                ) {
                    Text(text = "Back to Home", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    )
}
