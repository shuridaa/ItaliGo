package com.example.italigo.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import androidx.compose.foundation.background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ItaliGo",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White // White text for contrast
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF008C45) // Set the green color of the Italian flag for the TopAppBar
                )
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFF1F1F1)) // Light neutral color for the main content area
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), // Global padding for the content
                    verticalArrangement = Arrangement.spacedBy(16.dp), // Spacing between cards
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // New clickable box for Lesson 1
                    LessonCard(
                        lessonTitle = "Lesson 1: Greetings and Basic Phrases",
                        onClick = { navController.navigate("lesson_1") }
                    )

                    // New clickable box for Lesson 2
                    LessonCard(
                        lessonTitle = "Lesson 2: Numbers and Basic Counting",
                        onClick = { navController.navigate("lesson_2") }
                    )

                    // New clickable box for Lesson 3
                    LessonCard(
                        lessonTitle = "Lesson 3: Colours and Simple Objects",
                        onClick = { navController.navigate("lesson_3") }
                    )
                }
            }
        }
    )
}

@Composable
fun LessonCard(lessonTitle: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .shadow(4.dp, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = lessonTitle,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
