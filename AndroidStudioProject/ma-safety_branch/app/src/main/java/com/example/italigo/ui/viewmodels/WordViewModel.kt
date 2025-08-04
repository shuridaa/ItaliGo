package com.example.italigo.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.italigo.data.models.Word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.italigo.data.database.DeckDao
import com.example.italigo.data.repositories.DeckRepository
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.compose.runtime.State
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class WordViewModel @Inject constructor(
    private val repository: DeckRepository,
    private val deckDao: DeckDao
) : ViewModel() {

    private val _words = MutableStateFlow<List<Word>>(emptyList())
    val words: StateFlow<List<Word>> = _words

    fun loadAllWords() {
        viewModelScope.launch {
            // Collect the Flow and update the state
            repository.getAllWords().collect { wordList ->
                _words.value = wordList
            }
        }
    }

    fun addWord(english: String, italian: String) {
        viewModelScope.launch {
            repository.insertWord(
                Word(
                    english = english,
                    italian = italian
                )
            )
            loadAllWords()
        }
    }

    fun addWordToDeck(deckId: Int, wordId: Int) {
        viewModelScope.launch {
            repository.addWordToDeck(deckId, wordId)
        }
    }

    fun deleteWord(wordId: Int) {
        viewModelScope.launch {
            repository.deleteWordById(wordId)
            loadAllWords()
        }
    }

    fun toggleCardSide() {
        _showItalian.value = !_showItalian.value
    }

    // Flashcard Session Stuff
    // State to store the list of words for the current deck
    private var _wordsForDeck = mutableStateOf<List<Word>>(emptyList())
    val wordsForDeck: State<List<Word>> = _wordsForDeck

    private val _currentWordIndex = MutableStateFlow(0)
    val currentWordIndex: StateFlow<Int> get() = _currentWordIndex

    private val _currentWord = MutableStateFlow<Word?>(null)
    val currentWord: StateFlow<Word?> get() = _currentWord

    private val _isSessionActive = MutableStateFlow(false)
    val isSessionActive: StateFlow<Boolean> get() = _isSessionActive

    private val _showItalian = MutableStateFlow(false)
    val showItalian: StateFlow<Boolean> get() = _showItalian

    // Start flashcard session
    fun startFlashcardSession(deckId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val dueOrPendingWords =
                repository.getWordsForDeckWithState(deckId, listOf("due", "pending"))

                if (dueOrPendingWords.isNotEmpty()) {
                    _wordsForDeck.value = dueOrPendingWords
                    _currentWordIndex.value = 0
                    _currentWord.value = dueOrPendingWords.first() // Show first word
                    _isSessionActive.value = true
                } else {
                    _isSessionActive.value = false
                    _currentWord.value = null
                }
            }
        }

    // Move to the next card
    fun moveToNextWord(isCorrect: Boolean) {
        _showItalian.value = false // Reset to English side when moving to the next card

        viewModelScope.launch {
            _currentWord.value?.let { word ->
                val updatedWord = repository.updateCardState(word, isCorrect)
                repository.updateWordReview(updatedWord) // Update the database

                val currentList = _wordsForDeck.value.toMutableList()

                if (!isCorrect) {
                    // Move current card to the back
                    val currentIndex = _currentWordIndex.value
                    val card = currentList.removeAt(currentIndex)
                    currentList.add(card)
                }

                _wordsForDeck.value = currentList // Update the list

                // Update current word index
                if (currentList.isNotEmpty()) {
                    _currentWordIndex.value = (_currentWordIndex.value + 1) % currentList.size
                    _currentWord.value = currentList[_currentWordIndex.value]
                } else {
                    _isSessionActive.value = false
                    _currentWord.value = null
                }
            }
        }
    }


    // End flashcard session
        fun endFlashcardSession() {
            _isSessionActive.value = false
            _currentWord.value = null
            _currentWordIndex.value = 0
        }
    }

