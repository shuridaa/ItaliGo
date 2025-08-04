package com.example.italigo.data

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)

val lesson1Questions = listOf(
    QuizQuestion(
        question = "Match the Italian word to its English translation:\nBuongiorno",
        options = listOf("Goodbye", "Good morning", "Hello", "Thank you"),
        correctAnswer = "Good morning"
    ),
    QuizQuestion(
        question = "Fill in the blank: _Ciao_ means \"Hello\" or \"______\".",
        options = listOf("Goodbye", "Morning", "Welcome", "Evening"),
        correctAnswer = "Goodbye"
    ),
    QuizQuestion(
        question = "Which phrase means “Good evening”?",
        options = listOf("Buongiorno", "Buonasera", "Prego", "Arrivederci"),
        correctAnswer = "Buonasera"
    ),
    QuizQuestion(
        question = "You meet someone new. How would you say your name in Italian?",
        options = listOf("Grazie", "Mi chiamo", "Buonasera", "Per favore"),
        correctAnswer = "Mi chiamo"
    )
)

// Define the questions for Lesson 2
val lesson2Questions = listOf(
        QuizQuestion(
            question = "What is the Italian word for the number 1?",
            options = listOf("Uno", "Due", "Tre", "Quattro"),
            correctAnswer = "Uno"
        ),
        QuizQuestion(
            question = "How do you say 'two' in Italian?",
            options = listOf("Uno", "Due", "Tre", "Cinque"),
            correctAnswer = "Due"
        ),
        QuizQuestion(
            question = "Which number is 'Cinque' in Italian?",
            options = listOf("4", "5", "6", "7"),
            correctAnswer = "5"
        ),
        QuizQuestion(
            question = "Fill in the blank: 'Sette' means ____.",
            options = listOf("One", "Two", "Three", "Seven"),
            correctAnswer = "Seven"
        ),
        QuizQuestion(
            question = "Which number is 'Dieci' in Italian?",
            options = listOf("6", "7", "8", "10"),
            correctAnswer = "10"
        )
    )

// Define the questions for Lesson 3
val lesson3Questions = listOf(
        QuizQuestion(
            question = "What is the Italian word for the color red?",
            options = listOf("Blu", "Rosso", "Verde", "Giallo"),
            correctAnswer = "Rosso"
        ),
        QuizQuestion(
            question = "How do you say 'green' in Italian?",
            options = listOf("Rosso", "Verde", "Arancio", "Azzurro"),
            correctAnswer = "Verde"
        ),
        QuizQuestion(
            question = "Which color is 'Giallo' in Italian?",
            options = listOf("Blue", "Red", "Yellow", "Black"),
            correctAnswer = "Yellow"
        ),
        QuizQuestion(
            question = "Fill in the blank: 'Una mela ___' means 'A red apple.'",
            options = listOf("Blu", "Verde", "Rosso", "Giallo"),
            correctAnswer = "Rosso"
        ),
        QuizQuestion(
            question = "What does 'una sedia blu' mean?",
            options = listOf("A red chair", "A blue chair", "A green chair", "A yellow chair"),
            correctAnswer = "A blue chair"
        )
    )


