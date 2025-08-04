package com.example.italigo.ui.navigation

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.italigo.ui.view.DeckScreen
import com.example.italigo.ui.view.FlashcardScreen
import com.example.italigo.ui.view.HomeScreen
import com.example.italigo.ui.view.ProfileScreen
import com.example.italigo.ui.view.QuizScreen
import com.example.italigo.ui.view.WordScreen
import com.example.italigo.ui.view.DeckWordsScreen
import com.example.italigo.ui.view.FlashcardSessionScreen
import com.example.italigo.ui.view.DeckDetailsScreen
import com.example.italigo.ui.view.AddDeckScreen
import com.example.italigo.ui.view.Lesson1Screen
import com.example.italigo.ui.view.Lesson1ResultScreen
import com.example.italigo.ui.view.Lesson2Screen
import com.example.italigo.ui.view.Lesson2ResultScreen
import com.example.italigo.ui.view.Lesson3Screen
import com.example.italigo.ui.view.Lesson3ResultScreen
import com.example.italigo.ui.view.SplashScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.italigo.ui.view.ChooseWordsScreen

@Composable
fun AppCoordinator(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home", deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/home" })
        ) { HomeScreen(navController) }
        composable("flashcards", deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/flashcards" })
        ) { FlashcardScreen(navController) }
        composable("quiz",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/quiz" })
        ) { QuizScreen(navController) }
        composable("profile",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/profile" })
        ) { ProfileScreen(navController) }
        composable("flashdecks",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/flashdecks" })
        ) { DeckScreen(navController) }
        composable("words",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/words" })
        ) { WordScreen(navController) }
        composable("deck_words_screen/{deckId}",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/deck_words_screen{deckId}" })
        ) { backStackEntry ->
//            val deckId = backStackEntry.arguments?.getInt("deckId") ?: 0
            val deckId = backStackEntry.arguments?.getString("deckId")?.toIntOrNull() ?: 0
            DeckWordsScreen(navController, deckId) // Pass deckId to DeckWordsScreen
        }
        composable("deck_details_screen/{deckId}",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/deck_details_screen/{deckId}" })
        ) { backStackEntry ->
            val deckId = backStackEntry.arguments?.getString("deckId")?.toIntOrNull()
            if (deckId != null) {
                DeckDetailsScreen(navController, deckId)
            }
        }
        composable("choose_words_screen/{deckId}",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/choose_words_screen/{deckId}" })
        ) { backStackEntry ->
            val deckId = backStackEntry.arguments?.getString("deckId")?.toIntOrNull()
            if (deckId != null) {
                ChooseWordsScreen(navController, deckId)
            }
        }
        composable(
            "flashcards_session/{deckId}",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/flashcards_session/{deckId}" })
        ) { backStackEntry ->
            val deckId = backStackEntry.arguments?.getString("deckId")?.toIntOrNull()
            FlashcardSessionScreen(navController, deckId)
        }
        composable("lesson_1",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/lesson_1" })
        ) { Lesson1Screen(navController) }
        composable("lesson_1_results/{score}",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/lesson_1_results/{score}" })
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            Lesson1ResultScreen(navController, score) }
        composable("lesson_2",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/lesson_2" })
        ) { Lesson2Screen(navController) }
        composable("lesson_2_results/{score}",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/lesson_2_results/{score}" })
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            Lesson2ResultScreen(navController, score) }
        composable("lesson_3",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/lesson_3" })
        ) { Lesson3Screen(navController) }
        composable("lesson_3_results/{score}",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/lesson_3_results/{score}" })
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            Lesson3ResultScreen(navController, score) }
        composable("add_deck",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/add_deck" })
        ) { AddDeckScreen(navController) }
        composable("splash",
            deepLinks = listOf(navDeepLink { uriPattern = "android-app://com.example.italigo/splash" })
        ) { SplashScreen(navController) }
        // Add more destinations here
    }
}
