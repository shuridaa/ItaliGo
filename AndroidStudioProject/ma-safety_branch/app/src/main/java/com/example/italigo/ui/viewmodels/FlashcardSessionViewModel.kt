//package com.example.italigo.ui.viewmodels
//
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import com.example.italigo.data.models.Flashcard
//import com.example.italigo.data.repositories.FlashcardRepository
//
//class FlashcardSessionViewModel(private val repository: FlashcardRepository) : ViewModel() {
//
//    var flashcards by mutableStateOf<List<Flashcard>>(emptyList())
//        private set
//
//    fun startSession(deckId: Long) {
//        flashcards = repository.getFlashcardsForDeck(deckId)
//    }
//
//    fun endSession() {
//        flashcards = emptyList() // Logic to end session
//    }
//}
