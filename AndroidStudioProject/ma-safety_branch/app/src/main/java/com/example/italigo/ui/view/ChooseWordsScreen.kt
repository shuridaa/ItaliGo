package com.example.italigo.ui.view

import com.example.italigo.data.models.Word
import com.example.italigo.ui.viewmodels.WordViewModel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.draw.shadow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseWordsScreen(
    navController: NavController,
    deckId: Int,
    viewModel: WordViewModel = hiltViewModel()
) {
    val words by viewModel.words.collectAsState(emptyList())
    val selectedWords = remember { mutableStateListOf<Word>() }

    LaunchedEffect(Unit) {
        viewModel.loadAllWords()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F1F1)) // Light background color
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Select Words",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color(0xFF008C45) // Green color for the TopAppBar
                    )
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(Color(0xFFF1F1F1)) // Light background color
                ) {
                    // LazyColumn for word list
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp), // Space between items
                        contentPadding = PaddingValues(bottom = 80.dp) // Add padding to the bottom
                    ) {
                        items(words) { word ->
                            WordItem(
                                word = word,
                                isSelected = selectedWords.contains(word),
                                onSelectionChange = { isSelected ->
                                    if (isSelected) {
                                        selectedWords.add(word)
                                    } else {
                                        selectedWords.remove(word)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        )

        // Button at the top
        Button(
            onClick = {
                selectedWords.forEach { word ->
                    viewModel.addWordToDeck(deckId, word.id)
                }
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
                .width(150.dp) // Reduce button width
                .height(50.dp), // Consistent height
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF1F1F1), // Dark green button
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Add Selected",
                color = Color.Black
            )
        }
    }
}

@Composable
fun WordItem(
    word: Word,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp)
            .padding(horizontal = 4.dp), // Ensure items are padded slightly inside the LazyColumn
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = word.english,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = word.italian,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            Checkbox(
                checked = isSelected,
                onCheckedChange = onSelectionChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF008C45), // Green when checked
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                )
            )
        }
    }
}
