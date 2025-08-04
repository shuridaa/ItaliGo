package com.example.italigo.ui.view

import android.util.Log
import com.example.italigo.data.models.Deck
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.italigo.ui.viewmodels.DeckViewModel
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDeckScreen(
    navController: NavController,
    viewModel: DeckViewModel = hiltViewModel()
) {
    var deckName by remember { mutableStateOf("") }
    var deckDescription by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create New Deck",
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Deck name input
                OutlinedTextField(
                    value = deckName,
                    onValueChange = { deckName = it },
                    label = { Text("Deck Name") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = deckName.isEmpty()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Deck description input
                OutlinedTextField(
                    value = deckDescription,
                    onValueChange = { deckDescription = it },
                    label = { Text("Description (optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = false
                )

                // Error message if deck name is empty
                if (deckName.isEmpty()) {
                    Text(
                        text = "Deck name cannot be empty",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Create Deck Button
                Button(
                    onClick = {
                        if (deckName.isNotEmpty()) {
                            // Call addDeck with name and description
                            viewModel.addDeck(deckName, deckDescription)
                            navController.popBackStack()  // Navigate back after creating deck
                        } else {
                            errorMessage = "Please enter a valid deck name."
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF006400), // Dark green color
                        contentColor = Color.White // White text color for contrast
                    )
                ) {
                    Text("Create Deck")
                }

                // Display error if any
                errorMessage?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    )
}
