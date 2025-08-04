package com.example.italigo.ui.view

import com.example.italigo.ui.viewmodels.DeckViewModel
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import androidx.compose.ui.draw.shadow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckDetailsScreen(
    navController: NavController,
    deckId: Int,
    viewModel: DeckViewModel = hiltViewModel()
) {
    val deck by viewModel.getDeckById(deckId).collectAsState(initial = null)
    val words by viewModel.getWordsByDeckId(deckId).collectAsState(initial = emptyList()) // Observe words

    // Date formatter for timestamps
    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").withZone(ZoneId.systemDefault())

    if (deck == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...", style = MaterialTheme.typography.bodyLarge)
        }
        return
    }

    deck?.let { nonNullDeck ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Deck Details",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White
                        )
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color(0xFF008C45)
                    ),
                    modifier = Modifier.shadow(4.dp)
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = nonNullDeck.name,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(4.dp),
                        shape = MaterialTheme.shapes.medium.copy(CornerSize(12.dp))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Number of words: ${words.size}", // Use the size of the words list
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Button(
                                onClick = { navController.navigate("deck_words_screen/${nonNullDeck.id}") },
                                modifier = Modifier.padding(vertical = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF006400),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("View/Edit Words", style = MaterialTheme.typography.bodyLarge)
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            val createdAtFormatted = dateFormatter.format(Instant.ofEpochMilli(nonNullDeck.createdAt))
                            val updatedAtFormatted = dateFormatter.format(Instant.ofEpochMilli(nonNullDeck.updatedAt))
                            val lastStudiedFormatted = nonNullDeck.lastStudied?.let {
                                dateFormatter.format(Instant.ofEpochMilli(it))
                            } ?: "Never"

                            Text("Created: $createdAtFormatted", style = MaterialTheme.typography.bodyLarge)
                            Text("Last Updated: $updatedAtFormatted", style = MaterialTheme.typography.bodyLarge)
                            Text("Last Studied: $lastStudiedFormatted", style = MaterialTheme.typography.bodyLarge)
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(4.dp),
                        shape = MaterialTheme.shapes.medium.copy(CornerSize(12.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Start Flashcard Session Button
                            Button(
                                onClick = { navController.navigate("flashcards_session/${nonNullDeck.id}") },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF006400),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Start Flashcard Session", style = MaterialTheme.typography.bodyLarge)
                            }

                            // Delete Deck Button
                            Button(
                                onClick = {
                                    viewModel.deleteDeck(nonNullDeck)
                                    navController.popBackStack()
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                            ) {
                                Icon(Icons.Filled.Delete, contentDescription = "Delete Deck")
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Delete Deck")
                            }
                        }
                    }
                }
            }
        )
    }
}
