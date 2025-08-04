package com.example.italigo.data

import com.example.italigo.data.models.Deck
import com.example.italigo.data.models.Word
import com.example.italigo.data.models.DeckWordCrossRef

object SeedData {
    val decks = listOf(
        Deck(name = "Italian Basics", description = "Basic Italian vocabulary"),
        Deck(name = "Animals", description = "Italian words related to food and drinks")
    )

    val words = listOf(
        Word(english = "Bee", italian = "Ape"),
        Word(english = "Cat", italian = "Gatto"),
        Word(english = "Dog", italian = "Cane"),
        Word(english = "Bird", italian = "Uccello"),
        Word(english = "Backpack", italian = "Zaino"),
        Word(english = "Bag", italian = "Borsa"),
        Word(english = "Aunt", italian = "Zia"),
        Word(english = "Uncle", italian = "Zio"),
        Word(english = "Mother", italian = "Madre"),
        Word(english = "Father", italian = "Padre"),
        Word(english = "Brother", italian = "Fratello"),
        Word(english = "Sister", italian = "Sorella"),
        Word(english = "Big", italian = "Grande"),
        Word(english = "Bed", italian = "Letto"),
        Word(english = "Car", italian = "Macchina"),
        Word(english = "Ice-cream", italian = "Gelato")
    )

    val deckWordCrossRefs = listOf(
        DeckWordCrossRef(deckId = 2, wordId = 1),
        DeckWordCrossRef(deckId = 2, wordId = 2),
        DeckWordCrossRef(deckId = 2, wordId = 3),
        DeckWordCrossRef(deckId = 2, wordId = 4)
    )
}
