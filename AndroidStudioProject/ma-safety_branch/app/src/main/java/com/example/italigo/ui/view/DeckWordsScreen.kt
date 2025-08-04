package com.example.italigo.ui.view

import com.example.italigo.data.models.Word
import com.example.italigo.ui.viewmodels.DeckWordsViewModel

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckWordsScreen(
    navController: NavController,
    deckId: Int,
    viewModel: DeckWordsViewModel = hiltViewModel() // Hilt injects ViewModel
) {
    val words by viewModel.getWordsByDeckId(deckId).collectAsState(emptyList())

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Words in Deck",
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
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (words.isEmpty()) {
                            Text(
                                text = "No words in this deck. Add some words!",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        } else {
                            LazyColumn(modifier = Modifier.weight(1f)) {
                                items(words) { word ->
                                    DeckWordItem(
                                        word = word,
                                        onDelete = { viewModel.removeWordFromDeck(deckId, word.id) },
                                        modifier = Modifier.padding(bottom = 16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )

        FloatingActionButton(
            onClick = { navController.navigate("choose_words_screen/$deckId") },
            containerColor = Color(0xFFF1F1F1),
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Word/s",
                tint = Color.Black // Black icon color
            )
        }
    }
}

@Composable
fun DeckWordItem(word: Word, onDelete: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
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
                Text(text = word.english, style = MaterialTheme.typography.titleMedium)
                Text(text = word.italian, style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Word",
                    tint = Color.Black // Black icon color
                )
            }
        }
    }
}


//package com.example.italigo.ui.view
//
//import com.example.italigo.data.models.Word
//import com.example.italigo.ui.viewmodels.DeckWordsViewModel
//
//import android.util.Log
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//
//@Composable
//fun DeckWordsScreen(
//    navController: NavController,
//    deckId: Int,
//    viewModel: DeckWordsViewModel = hiltViewModel() // Hilt injects ViewModel
//) {
//    // Observe words for the selected deck
//    val words by viewModel.getWordsByDeckId(deckId).collectAsState(emptyList())
//
//    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        Column(modifier = Modifier.fillMaxSize()) {
//            Text(
//                text = "Words in Deck",
//                style = MaterialTheme.typography.headlineSmall,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            // Display a message if no words are available in the deck
//            if (words.isEmpty()) {
//                Text(
//                    text = "No words in this deck. Add some words!",
//                    style = MaterialTheme.typography.bodyLarge,
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//            } else {
//                LazyColumn(modifier = Modifier.weight(1f)) {
//                    items(words) { word ->
//                        DeckWordItem(
//                            word = word,
//                            onDelete = { viewModel.removeWordFromDeck(deckId, word.id) }
//                        )
//                    }
//                }
//            }
//        }
//
//        // Floating Action Button to add words
//        FloatingActionButton(
//            onClick = {
//                navController.navigate("choose_words_screen/$deckId") // Navigate to ChooseWordsScreen
//            },
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(16.dp)
//        ) {
//            Icon(
//                imageVector = Icons.Default.Add,
//                contentDescription = "Add Word/s"
//            )
//        }
//    }
//}
//
//@Composable
//fun DeckWordItem(word: Word, onDelete: () -> Unit) {
//    Card(modifier = Modifier
//        .fillMaxWidth()
//        .padding(8.dp)) {
//        Row(
//            modifier = Modifier.padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(modifier = Modifier.weight(1f)) {
//                Text(text = word.english, style = MaterialTheme.typography.titleMedium)
//                Text(text = word.italian, style = MaterialTheme.typography.bodyMedium)
//            }
//            IconButton(onClick = onDelete) {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Delete Word"
//                )
//            }
//        }
//    }
//}
