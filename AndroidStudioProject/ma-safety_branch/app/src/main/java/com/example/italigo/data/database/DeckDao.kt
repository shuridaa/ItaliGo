package com.example.italigo.data.database

import com.example.italigo.data.models.Deck

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.*
import com.example.italigo.data.models.DeckWordCrossRef
import com.example.italigo.data.models.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface DeckDao {
    // READ: Get all words from the table
    @Query("SELECT * FROM words")
    fun getAllWords(): Flow<List<Word>>

    // READ: Get a single word by its ID
    @Query("SELECT * FROM words WHERE id = :id")
    suspend fun getWordById(id: Int): Word?

    // CREATE: Insert a word into the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word): Long

    // CREATE: Insert multiple words into the table
    /* Example Implementation:
    val newWords = listOf(
          Word(english = "Bee", italian = "Ape"),
          Word(english = "Cat", italian = "Gatto"),
          Word(english = "Dog", italian = "Cane"),
          Word(english = "Bird", italian = "Uccello")
      )
      wordDao.insertWords(newWords)
    * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWords(words: List<Word>): List<Long>

    // UPDATE: Update an existing word
    @Update
    suspend fun updateWord(word: Word): Int

    // UPDATE: Update multiple words
    /* Example Implementation:
    val updatedWords = listOf(
            Word(id = 9, english = "Cat", italian = "Gatto", isLearned = true),
            Word(id = 17, english = "Bird", italian = "Uccello", isLearned = true)
        )
      wordDao.updateWords(updatedWords)
    * */
    @Update
    suspend fun updateWords(words: List<Word>): Int

    @Query("DELETE FROM words WHERE id = :wordId")
    suspend fun deleteWordById(wordId: Int): Int

    // FLASHCARD WORD QUERIES:
    @Query("SELECT * FROM words WHERE state = :state")
    suspend fun getWordsByState(state: String): List<Word> // General method for state queries

    @Query("SELECT * FROM words WHERE state = 'pending'")
    suspend fun getPendingWords(): List<Word>

    @Query("SELECT * FROM words WHERE state = 'due'")
    suspend fun getDueWords(): List<Word>

    @Query("SELECT * FROM words WHERE state = 'not_due'")
    suspend fun getNotDueWords(): List<Word>

    @Query("UPDATE words SET lastReviewed = :lastReviewed, reviewInterval = :interval, state = :state WHERE id = :id")
    suspend fun updateWordReview(id: Int, lastReviewed: Long, interval: Int, state: String)

    @Query("UPDATE words SET lastReviewed = :lastReviewed, reviewInterval = :interval, state = :state WHERE id IN (:ids)")
    suspend fun updateMultipleWordReviews(ids: List<Int>, lastReviewed: Long, interval: Int, state: String)

    // Get words by deck ID
    @Query("""
        SELECT * FROM words 
        WHERE id IN (SELECT wordId FROM deck_word_cross_ref WHERE deckId = :deckId)
    """)
    fun getWordsByDeckId(deckId: Int): Flow<List<Word>>

    @Query(
        """
        DELETE FROM deck_word_cross_ref 
        WHERE deckId = :deckId AND wordId = :wordId
        """
    )
    suspend fun deleteDeckWordCrossRef(deckId: Int, wordId: Int)

    @Transaction
    @Query("""
        SELECT w.* 
        FROM words w
        JOIN deck_word_cross_ref dwcr ON w.id = dwcr.wordId
        WHERE dwcr.deckId = :deckId AND w.state IN (:states)
    """)
    fun getWordsForDeckWithState(deckId: Int, states: List<String>): List<Word>


    @Query("SELECT * FROM decks")
    suspend fun getAllDecks(): List<Deck>

    @Query("SELECT * FROM decks WHERE id = :deckId")
    fun getDeckById(deckId: Int): Flow<Deck?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDeck(deck: Deck)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDecks(decks: List<Deck>)

    @Delete
    suspend fun deleteDeck(deck: Deck)

    // Get all words in a specific deck
    @Transaction
    @Query("SELECT * FROM words INNER JOIN deck_word_cross_ref ON words.id = deck_word_cross_ref.wordId WHERE deck_word_cross_ref.deckId = :deckId")
    suspend fun getWordsInDeck(deckId: Int): List<Word>

    // Insert a word into a deck
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDeckWordCrossRef(crossRef: DeckWordCrossRef)

    @Query("SELECT name FROM decks WHERE id = :deckId")
    suspend fun getDeckName(deckId: Int): String?

}
