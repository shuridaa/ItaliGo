package com.example.italigo.ui.viewmodels

import com.example.italigo.data.models.Deck
import com.example.italigo.data.repositories.DeckRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.italigo.data.models.Word
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DeckViewModel @Inject constructor(
    private val repository: DeckRepository
) : ViewModel() {

    private val _decks = MutableStateFlow<List<Deck>>(emptyList())
    val decks: StateFlow<List<Deck>> = _decks

    init {
        loadDecks()
    }

    private fun loadDecks() {
        viewModelScope.launch {
            _decks.value = repository.getAllDecks()
        }
    }

    fun addDeck(name: String, description: String?) {
        viewModelScope.launch {
            repository.insertDeck(Deck(name = name, description = description))
            loadDecks()
        }
    }

    fun deleteDeck(deck: Deck) {
        viewModelScope.launch {
            repository.deleteDeck(deck)
            loadDecks()
        }
    }

    fun showAddDeckDialog() {
        // Logic to show add deck dialog
    }

    fun getDeckById(deckId: Int): Flow<Deck?> {
        return repository.getDeckById(deckId)
    }

    // Get words by deck ID
    fun getWordsByDeckId(deckId: Int): Flow<List<Word>> {
        return repository.getWordsByDeckId(deckId)
    }
}