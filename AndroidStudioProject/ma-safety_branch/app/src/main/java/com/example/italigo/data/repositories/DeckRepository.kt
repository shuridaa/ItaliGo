package com.example.italigo.data.repositories

import androidx.room.Query
import com.example.italigo.data.database.DeckDao
import com.example.italigo.data.models.Deck
import com.example.italigo.data.models.DeckWordCrossRef
import com.example.italigo.data.models.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
//import com.example.italigo.data.models.Flashcard

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeckRepository @Inject constructor(
    private val deckDao: DeckDao
) {
    // Get all words
    suspend fun getAllWords(): Flow<List<Word>> = withContext(Dispatchers.IO) {
        deckDao.getAllWords()
    }

    // Get a word by ID
    suspend fun getWordById(id: Int): Word? = withContext(Dispatchers.IO) {
        deckDao.getWordById(id)
    }

    // Insert a single word
    suspend fun insertWord(word: Word): Long = withContext(Dispatchers.IO) {
        deckDao.insertWord(word)
    }

    // Insert multiple words
    suspend fun insertWords(words: List<Word>): List<Long> = withContext(Dispatchers.IO) {
        deckDao.insertWords(words)
    }

    // Update a single word
    suspend fun updateWord(word: Word): Int = withContext(Dispatchers.IO) {
        deckDao.updateWord(word)
    }

    // Update multiple words
    suspend fun updateWords(words: List<Word>): Int = withContext(Dispatchers.IO) {
        deckDao.updateWords(words)
    }

    // Delete a word by ID
    suspend fun deleteWordById(wordId: Int): Int = withContext(Dispatchers.IO) {
        deckDao.deleteWordById(wordId)
    }

    // Get words by state (general query)
    suspend fun getWordsByState(state: String): List<Word> = withContext(Dispatchers.IO) {
        deckDao.getWordsByState(state)
    }

    // Get specific words for flashcards
    suspend fun getPendingWords(): List<Word> = withContext(Dispatchers.IO) {
        deckDao.getPendingWords()
    }

    suspend fun getDueWords(): List<Word> = withContext(Dispatchers.IO) {
        deckDao.getDueWords()
    }

    suspend fun getNotDueWords(): List<Word> = withContext(Dispatchers.IO) {
        deckDao.getNotDueWords()
    }

    // Update the review state of a single word
    suspend fun updateWordReview(word: Word): Unit = withContext(Dispatchers.IO) {
        deckDao.updateWordReview(
            id = word.id,
            lastReviewed = word.lastReviewed,
            interval = word.reviewInterval,
            state = word.state
        )
    }

    // Batch update review state for multiple words
    suspend fun updateMultipleWordReviews(
        words: List<Word>,
        lastReviewed: Long,
        interval: Int,
        state: String
    ): Unit = withContext(Dispatchers.IO) {
        deckDao.updateMultipleWordReviews(
            ids = words.map { it.id },
            lastReviewed = lastReviewed,
            interval = interval,
            state = state
        )
    }

    // Business logic for updating card state
    fun updateCardState(word: Word, isCorrect: Boolean): Word {
        val currentTime = System.currentTimeMillis()
        val newInterval = if (isCorrect) {
            when (word.reviewInterval) {
                0 -> 1 // First success: review in 1 day
                1 -> 3 // Second success: review in 3 days
                3 -> 7 // Third success: review in 7 days
                else -> word.reviewInterval * 2 // Double interval for subsequent successes
            }
        } else {
            1 // Reset to 1 day if incorrect
        }

        val newState = if (newInterval == 1) "due" else "not_due"
        return word.copy(
            lastReviewed = currentTime,
            reviewInterval = newInterval,
            state = newState
        )
    }

    // DECK METHODS:
    // Get words by deck ID
    fun getWordsByDeckId(deckId: Int): Flow<List<Word>> {
        return deckDao.getWordsByDeckId(deckId)
    }

    suspend fun removeWordFromDeck(deckId: Int, wordId: Int) {
        deckDao.deleteDeckWordCrossRef(deckId, wordId)
    }

    // Get all decks
    suspend fun getAllDecks(): List<Deck> {
        return deckDao.getAllDecks()
    }

    // Get specific deck by ID
    fun getDeckById(deckId: Int): Flow<Deck?> {
        return deckDao.getDeckById(deckId)
    }

    // Insert a new deck
    suspend fun insertDeck(deck: Deck) {
        deckDao.insertDeck(deck)
    }

    // Delete a deck
    suspend fun deleteDeck(deck: Deck) {
        deckDao.deleteDeck(deck)
    }

    // Add a word/s to a deck
    suspend fun addWordToDeck(deckId: Int, wordId: Int) {
        val crossRef = DeckWordCrossRef(deckId = deckId, wordId = wordId)
        deckDao.insertDeckWordCrossRef(crossRef)
    }

    suspend fun getWordsForDeckWithState(deckId: Int, states: List<String>): List<Word> {
        return withContext(Dispatchers.IO) { deckDao.getWordsForDeckWithState(deckId, states) }
    }
}