package com.example.italigo.ui.viewmodels

import com.example.italigo.data.models.Word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.italigo.data.repositories.DeckRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DeckRepository) : ViewModel() {
    private val _words = MutableStateFlow<List<Word>>(emptyList())
    val words: StateFlow<List<Word>> get() = _words

    fun fetchWords() {
        viewModelScope.launch(Dispatchers.IO) {
            // Collect the Flow and update the state with the data
            repository.getAllWords().collect { wordList ->
                _words.value = wordList
            }
        }
    }
}
