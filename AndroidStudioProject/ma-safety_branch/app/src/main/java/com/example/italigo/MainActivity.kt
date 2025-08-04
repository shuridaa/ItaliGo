package com.example.italigo

import com.example.italigo.ui.navigation.AppCoordinator
import com.example.italigo.ui.theme.ItaliGoTheme
import com.example.italigo.data.database.AppDatabase
import com.example.italigo.data.SeedData

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() { }

//@Composable
//fun AppCoordinator(navController: NavHostController) {
//    NavHost(navController = navController, startDestination = "splash") {
//        composable("splash") {
//            SplashScreen(navController = navController)
//        }
//        composable("profile_details") {
//            ProfileScreen(navController = navController)
//        }
//        composable("home") {
//            // Home screen composable
//        }
//    }
//}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var database: AppDatabase // Inject the database instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Populate the database using SeedData
        lifecycleScope.launch {
            val deckDao = database.deckDao()

            // Insert decks if the table is empty
            if (deckDao.getAllDecks().isEmpty()) {
                deckDao.insertDecks(SeedData.decks)
            }

            // Insert words if the table is empty
            deckDao.insertWords(SeedData.words)

            // Insert deck-word relationships if the table is empty
            SeedData.deckWordCrossRefs.forEach { deckDao.insertDeckWordCrossRef(it) }

            // DB CRUD ops tests
            val wordDao = database.deckDao()
            lifecycleScope.launch {
                val words = deckDao.getAllWords()
                Log.d("DatabaseTest", "Words: $words")
            }

            enableEdgeToEdge()
            setContent {
                ItaliGoTheme {
                    val navController = rememberNavController() // Initializing NavController
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            BottomNavigationBar(navController)
                        }
                    ) { innerPadding ->
                        AppCoordinator(navController = navController) // Wrapping the AppCoordinator in a Scaffold for UI consistency
                        Greeting(
                            name = "",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        NavigationBar(
            // Set the background color to red
            containerColor = Color(0xFFB73A3A) // Italian flag red color
        ) {
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate("home") },
                label = { Text("Home", color = Color.White) },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White) }
            )
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate("profile") },
                label = { Text("Profile", color = Color.White) },
                icon = { Icon(Icons.Default.Face, contentDescription = "Profile", tint = Color.White) }
            )
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate("flashdecks") },
                label = { Text("Flash", color = Color.White) },
                icon = { Icon(Icons.Default.MoreVert, contentDescription = "Flashdecks", tint = Color.White) }
            )
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate("words") },
                label = { Text("Words", color = Color.White) },
                icon = { Icon(Icons.Default.Search, contentDescription = "Words", tint = Color.White) }
            )
//            NavigationBarItem(
//                selected = false,
//                onClick = { navController.navigate("settings") },
//                label = { Text("Settings", color = Color.White) },
//                icon = { Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White) }
//            )
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewMainActivity() {
        ItaliGoTheme {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(navController)
                }
            ) { innerPadding ->
                // Apply the innerPadding to the content
                Box(modifier = Modifier.padding(innerPadding)) {
                    AppCoordinator(navController = navController)
                }
            }
        }
    }
}