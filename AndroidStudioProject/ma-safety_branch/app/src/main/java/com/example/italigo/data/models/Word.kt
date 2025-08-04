package com.example.italigo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index


@Entity(tableName = "words",
//    foreignKeys = [
//    ForeignKey(
//        entity = Deck::class,
//        parentColumns = ["id"],
//        childColumns = ["deckId"],
//        onDelete = ForeignKey.CASCADE
//    )
//],
    indices = [Index(value = ["english"], unique = true)] // Ensure the "word" column is unique
)
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val english: String,
    val italian: String,
    val isLearned: Boolean = false,
    val lastReviewed: Long = 0L, // Timestamp of last review
    val reviewInterval: Int = 0, // Days until next review
    val state: String = "pending", // 'pending', 'due', 'not_due'
    //val deckId: Int // Foreign key to Deck
)