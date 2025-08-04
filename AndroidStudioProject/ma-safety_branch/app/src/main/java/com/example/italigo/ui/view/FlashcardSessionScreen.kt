package com.example.italigo.ui.view

import com.example.italigo.ui.viewmodels.WordViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashcardSessionScreen(
    navController: NavController,
    deckId: Int?,
    wordViewModel: WordViewModel = hiltViewModel()
) {
    if (deckId == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No Deck Selected", style = MaterialTheme.typography.headlineMedium)
        }
        return
    }

    val isSessionActive by wordViewModel.isSessionActive.collectAsState()
    val currentWord by wordViewModel.currentWord.collectAsState()
    val showItalian by wordViewModel.showItalian.collectAsState()

    // Start the session if it hasn't started yet
    if (!isSessionActive) {
        wordViewModel.startFlashcardSession(deckId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Flashcard Session",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF006400) // Dark green color for the TopAppBar
                )
            )
        },
        content = { padding ->
            // UI based on session state
            if (isSessionActive) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Button to toggle the card side
                    Button(
                        onClick = { wordViewModel.toggleCardSide() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF006400), // Dark green button color
                            contentColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (showItalian) "Show English" else "Show Italian")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = if (showItalian) currentWord?.italian ?: "No word" else currentWord?.english ?: "No word",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Correct / Incorrect buttons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = { wordViewModel.moveToNextWord(isCorrect = true) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF006400), // Dark green color
                                contentColor = Color.White
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Correct")
                        }

                        Button(
                            onClick = { wordViewModel.moveToNextWord(isCorrect = false) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF006400), // Dark green color
                                contentColor = Color.White
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Incorrect")
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (currentWord == null) {
                        // No due or pending words
                        Text(
                            text = "No due or pending words for Deck ID: $deckId!",
                            fontSize = 24.sp,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        // Session finished
                        Text(
                            text = "Flashcard session finished for Deck ID: $deckId!",
                            fontSize = 24.sp,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { wordViewModel.startFlashcardSession(deckId) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF006400), // Dark green color
                            contentColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Start New Session")
                    }
                }
            }
        }
    )
}


//package com.example.italigo.ui.view
//
//import com.example.italigo.ui.viewmodels.WordViewModel
//
//import android.util.Log
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.ui.unit.sp
//
//@Composable
//fun FlashcardSessionScreen(
//    navController: NavController,
//    deckId: Int?,
//    wordViewModel: WordViewModel = hiltViewModel()
//) {
//    if (deckId == null) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("No Deck Selected", style = MaterialTheme.typography.headlineMedium)
//        }
//        return
//    }
//
//    val isSessionActive by wordViewModel.isSessionActive.collectAsState()
//    val currentWord by wordViewModel.currentWord.collectAsState()
//    val showItalian by wordViewModel.showItalian.collectAsState()
//
//    // Start the session if it hasn't started yet
//    if (!isSessionActive) {
//        wordViewModel.startFlashcardSession(deckId)
//    }
//
//    // UI based on session state
//    if (isSessionActive) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Deck Name
//            Text(
//                text = "Flashcard Session",
//                style = MaterialTheme.typography.headlineSmall,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Button to toggle the card side
//            Button(onClick = { wordViewModel.toggleCardSide() }) {
//                Text(if (showItalian) "Show English" else "Show Italian")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = if (showItalian) currentWord?.italian ?: "No word" else currentWord?.english ?: "No word",
//                //text = currentWord?.english ?: "No word",
//                style = MaterialTheme.typography.bodyLarge,
//                fontSize = 24.sp
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(onClick = { wordViewModel.moveToNextWord(isCorrect = true) }) {
//                Text("Correct")
//            }
//            Button(onClick = { wordViewModel.moveToNextWord(isCorrect = false) }) {
//                Text("Incorrect")
//            }
//        }
//    } else {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            if (currentWord == null) {
//                // No due or pending words
//                Text(
//                    text = "No due or pending words for Deck ID: $deckId!",
//                    fontSize = 24.sp
//                )
//            } else {
//                // Session finished
//                Text(
//                    text = "Flashcard session finished for Deck ID: $deckId!",
//                    fontSize = 24.sp
//                )
//            }
//            Button(onClick = { wordViewModel.startFlashcardSession(deckId) }) {
//                Text("Start New Session")
//            }
//        }
//    }
//}
