package com.example.italigo.ui.view

import com.example.italigo.data.lesson1Questions

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import kotlinx.coroutines.delay
import androidx.compose.ui.draw.shadow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lesson1Screen(navController: NavController) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var answerFeedback by remember { mutableStateOf<String?>(null) } // Track answer feedback
    val currentQuestion = lesson1Questions[currentQuestionIndex]

    // Use LaunchedEffect to handle the delay inside the composable scope
    LaunchedEffect(answerFeedback) {
        if (answerFeedback != null) {
            delay(1000) // Wait for 1 second to show feedback
            // Move to the next question or go to the result screen
            if (currentQuestionIndex < lesson1Questions.lastIndex) {
                currentQuestionIndex++
            } else {
                navController.navigate("lesson_1_results/$score")
            }
            answerFeedback = null // Reset feedback after delay
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lesson 1",
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
                verticalArrangement = Arrangement.Top, // Align content to the top
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title Text
                Text(
                    text = "Greetings and Basic Phrases",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally) // Centered title
                )

                // Question Text
                Text(
                    text = currentQuestion.question,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally) // Centered question
                )

                // Display answer feedback if available
                answerFeedback?.let {
                    Text(
                        text = it,
                        color = if (it == "Correct!") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterHorizontally) // Centered feedback
                    )
                }

                // Display options with dark green buttons
                currentQuestion.options.forEach { option ->
                    Button(
                        onClick = {
                            // Check if the answer is correct or incorrect
                            if (option == currentQuestion.correctAnswer) {
                                score++
                                answerFeedback = "Correct!"
                            } else {
                                answerFeedback = "Incorrect!"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .align(Alignment.CenterHorizontally), // Centered button
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF006400), // Dark green color
                            contentColor = Color.White // White text color for contrast
                        ),
                        shape = MaterialTheme.shapes.medium // Rounded corners
                    ) {
                        Text(text = option, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    )
}