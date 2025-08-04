Context / High Concept:
 The app is a user-friendly language learning tool designed to help users master Italian through
 interactive lessons and customizable flashcards. With a clean interface, personalized profiles,
 and progress tracking, it’s perfect for travelers, learners, and anyone looking to build their
 Italian skills with ease.
 ---------------------------------------
 
 Target Users:
 The app is designed for language learners, travellers, and bilingual individuals, primarily those
 interested in improving communication between English and Italian. Ideal for students,
 professionals, and tourists who value quick, accurate translations with minimal complexity. It
 caters to both tech-savvy users and beginners seeking a simple, reliable tool.
 User Stories:
 1. Language Learner: Maria, a 22-year-old student learning Italian, uses the app to
 translate vocabulary and save key phrases for her language practice sessions. She loves
 listening to translations via text-to-speech to perfect her pronunciation.
 2. Traveler: John, a 35-year-old tourist visiting Rome, uses the app to quickly translate
 English phrases into Italian when ordering at restaurants or asking for directions. He
 saves commonly used phrases like "Where is the nearest bus stop?" for easy access
 offline.
 3. Child: A 9-year-old who loves to read books in both English and Italian. She uses a
 special app that helps her understand new words in both languages. When she comes
 across a word she doesn't know, Elena types it into the app, and it quickly shows her the
 meaning in the other language. This helps her learn faster and have more fun while
 reading!
---------------------------------------
Initial Research:
 1. Existing Translation Apps (Google Translate, iTranslate, Microsoft Translator):
 o Strengths: Wide language support, voice input/output, offline mode, and
 user-friendly interfaces.
 o Weaknesses:Overwhelming feature sets for beginners, lack of personalized
 phrase saving, and limited customization options.
 o Takeaway:Focus on simplicity, personalization, and beginner-friendly design
 while ensuring essential features like voice input/output and offline usage.
 2. Language Learning Apps (Duolingo, Memrise):
 o Strengths: Gamified experiences and phrase repetition for retention.
 o Weaknesses:Often prioritize long-term learning over quick utility.
 o Takeaway:Incorporate a lightweight "favorites" system for repeat use of phrases,
 like flashcard features.
 3. Productivity Apps (Notion, Evernote):
 o Strengths: Clean UI, focus on saving and categorizing data.
 o Takeaway:Addaclear, intuitive system for saving, categorizing, and editing saved
 phrases for easy recall.
 4. Inspiration from Simple Utility Apps (Weather, Calculator):
 o Strengths: Minimalistic, focused interfaces that perform core tasks effortlessly.
 o Takeaway:Prioritize a no-frills approach where users can translate, save, and
 access phrases with minimal navigation.
 ● OverallGoal: Combine the efficiency of a translation app, customizability of a learning
 tool, and simplicity of a utility app for a well-rounded user experience.

----------------------------------------------------

Functional / Non-functional Requirements:
 Functional Requirements
 1. CoreFeatures:
 o Quizzes: Lessons in the form of multiple choice quizzes to teach and test vocab
 and grammar.
 o Flashcards: Flashcard sessions via user created flashcard decks using words
 provided by the database.
 o Profile: Stores user name, email address and an uploaded picture/avatar.
 o Words:Provides a viewable list of all the words/vocab contained in the database.
 2. User Interaction:
 o PhraseSaving: Use local storage (SQLite) to save and retrieve frequently used
 phrases.
 o ClearInterface: Simple navigation with buttons for translation, saving, and
 accessing words and phrases.
 3. UI/UX:
 o Designcomponents in Figma with a beginner-friendly layout.
 o Supportdynamic layouts for various screen sizes (responsive design).
 Non-functional Requirements
 1. Performance:
 o Fastresponse times for translations (<1 second for online, <2 seconds for offline).
 o Efficient local storage access for saved words and phrases.
 2. Scalability:
 o Designtheapptosupport additional languages in the future.
 o Usemodularcomponentsfor easy feature expansion (e.g., adding new APIs).
 o UseMVVM-Carchitecture for easy scaling in respect to new screens and
 navigation.
 3. Reliability:
 o Ensureaccurate translations and robust error handling (e.g., network issues,
 voice recognition errors).
o Regularly update offline language packs.
 4. Security:
 o Storesensitive user preferences securely using encrypted SharedPreferences or
 local databases.
 o Avoidstoring user data on external servers to maintain privacy.

---------------------------------------------

Technical Requirements
 1. Development Tools:
 o AndroidStudio: Primary IDE for development.
 o Kotlin: Programming language for app development.
 o Figma:Design tool for prototyping and UI design.
 o Gitlab: Version control.
 2. Storage:
 o LocalDatabase (SQLite): Save words, phrases and metadata.
 3. Asset Collections:
 o IconsfromIconify or Material Icons for a clean design.
 o Stockimages/illustrations from Unsplash or Blush for UI.

-----------------------------------------
 Development Approach and System Architecture
 1. Architecture:
 o FollowMVVM-C(Model-View-ViewModel-Controller) for modularity and
 separation of concerns.
 2. Development Methodology:
 o FollowAgile methodology with sprints for iterative development.
 o Conductusability testing after each sprint to refine the design and functionality.

----------------------------------------
 Database and Local Storage
 1. Database:
 o UseSQLitetostore:
 ▪ Savedphrases (text, timestamp, usage frequency, etc).
----------------------------------
UI Requirements:
 1. Navigation Components
 ● BottomNavigation Bar:
 o Forswitching between key screens like "Home," "Profile," “Flashcards” and
 “Words".
 o Source:Material Design Bottom Navigation
 3. Saved Words and Phrases Screen
 ● CardViewforPhrases:
 o Ascrollable list of saved words/phrases displayed in cards.
 o Source:Material Design Cards
 5. Visual Enhancements
 ● Icons:
 o Useiconsfor delete and bottom navigation.
 o Source:Material Icons Library
 ● Illustrations:
 o Addsubtle, engaging illustrations on the empty screens (e.g., no saved phrases).
 o Addasplashscreen with a logo.
 o Source:Blush Illustrations
 6. Feedback and Alerts
 ● Snackbars andToasts:
 o Display quick messages for user actions like "Correct" or "Incorrect" when doing
 quizzes.
 o Source:Material Design Snackbars
 ● DialogBoxes:
 o Forconfirmation actions like deleting a saved phrase or resetting settings.
 o Source:Material Design Dialogs






