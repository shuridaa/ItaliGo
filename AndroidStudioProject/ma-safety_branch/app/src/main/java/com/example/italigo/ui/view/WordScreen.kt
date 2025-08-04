package com.example.italigo.ui.view

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.italigo.ui.viewmodels.WordViewModel
import com.example.italigo.data.models.Word
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.ui.draw.shadow
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordScreen(
    navController: NavController,
    viewModel: WordViewModel = hiltViewModel()
) {
    val words by viewModel.words.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAllWords()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Words",
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
                    verticalArrangement = Arrangement.spacedBy(16.dp), // Spacing between cards
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Display a message if there are no words
                    if (words.isEmpty()) {
                        Text(
                            text = "No words available. Add your first word!",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    } else {
                        LazyColumn(modifier = Modifier.weight(1f).padding(bottom = 72.dp)) {
                            items(words) { word ->
                                WordItem(
                                    word = word,
                                    modifier = Modifier.padding(bottom = 16.dp) // Padding between items
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun WordItem(word: Word, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = {}) // Add functionality here if needed
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
                text = word.english,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = word.italian,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
