package com.example.italigo.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.italigo.data.database.DeckDao
import com.example.italigo.data.models.DeckWordCrossRef
import com.example.italigo.data.models.Word
import com.example.italigo.data.repositories.DeckRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeckWordsViewModel @Inject constructor(
    private val repository: DeckRepository,
    private val deckDao: DeckDao
) : ViewModel() {

    // Function to get words by deck ID
    fun getWordsByDeckId(deckId: Int): Flow<List<Word>> {
        return repository.getWordsByDeckId(deckId)
    }

    fun removeWordFromDeck(deckId: Int, wordId: Int) {
        viewModelScope.launch {
            repository.removeWordFromDeck(deckId, wordId)
        }
    }

    // Get all words in the database
    fun getAllWords(): Flow<List<Word>> = deckDao.getAllWords()

    // Add a word to the deck
    fun addWordToDeck(deckId: Int, wordId: Int) {
        val crossRef = DeckWordCrossRef(deckId = deckId, wordId = wordId)

        // Insert the cross reference into the database
        viewModelScope.launch {
            deckDao.insertDeckWordCrossRef(crossRef)
        }
    }
    // Other functions
}
