package com.example.italigo.data.database

import com.example.italigo.data.models.Word
import com.example.italigo.data.models.Deck
import com.example.italigo.data.models.DeckWordCrossRef

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class, Deck::class, DeckWordCrossRef::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deckDao(): DeckDao
}
