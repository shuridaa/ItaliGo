package com.example.italigo.ui.view

import android.util.Log
import com.example.italigo.data.models.Deck

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.italigo.ui.viewmodels.DeckViewModel
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckScreen(
    navController: NavController,
    viewModel: DeckViewModel = hiltViewModel()
) {
    val decks by viewModel.decks.collectAsState(emptyList())

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Flashcard Decks",
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(Color(0xFFF1F1F1)) // Light neutral color for the main content area
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp), // Reduced space between cards
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Display a message if there are no decks
                        if (decks.isEmpty()) {
                            Text(
                                text = "No decks available. Create your first deck!",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        } else {
                            LazyColumn(modifier = Modifier.weight(1f)) {
                                items(decks) { deck ->
                                    DeckItem(
                                        deck = deck,
                                        onClick = { navController.navigate("deck_details_screen/${deck.id}") },
                                        onDelete = { viewModel.deleteDeck(deck) },
                                        modifier = Modifier.padding(bottom = 16.dp) // Adding padding between items
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )

        // FAB placed in the Box, outside of the Scaffold content, overlapping the top app bar
        FloatingActionButton(
            onClick = { navController.navigate("add_deck") },
            containerColor = Color(0xFFF1F1F1),
            contentColor = Color.White, // White icon color
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp) // Positioning the FAB above the top app bar
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Deck",
                tint = Color.Black // White icon color
            )
        }
    }
}

@Composable
fun DeckItem(deck: Deck, onClick: () -> Unit, onDelete: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .shadow(4.dp, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = deck.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = deck.description ?: "No description",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Deck", tint = Color.Black) // Black icon color
            }
        }
    }
}
