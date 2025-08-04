package com.example.italigo.data.models

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "deck_word_cross_ref",
    primaryKeys = ["deckId", "wordId"], // Composite primary key
    indices = [Index(value = ["deckId"]), Index(value = ["wordId"])]
)
data class DeckWordCrossRef(
    val deckId: Int, // Foreign key to the Deck table
    val wordId: Int // Foreign key to the Word table
)
