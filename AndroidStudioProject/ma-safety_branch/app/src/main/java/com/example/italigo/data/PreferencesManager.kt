//package com.example.italigo.data
//
//import android.content.Context
//import android.content.SharedPreferences
//import dagger.hilt.android.qualifiers.ApplicationContext
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.withContext
//import javax.inject.Inject
//
//class PreferencesManager @Inject constructor(
//    @ApplicationContext private val context: Context // Injecting the application context
//) {
//    private val sharedPreferences: SharedPreferences =
//        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
//
//    private val DARK_MODE_KEY = "dark_mode_key"
//
//    // Read the current theme preference using Flow
//    private val _isDarkModeFlow = MutableStateFlow(getDarkModePreference())
//    val isDarkModeFlow: Flow<Boolean> = _isDarkModeFlow
//
//    // Save the theme preference
//    suspend fun setDarkMode(isDarkMode: Boolean) {
//        withContext(Dispatchers.IO) {
//            sharedPreferences.edit().putBoolean(DARK_MODE_KEY, isDarkMode).apply()
//        }
//        // Update the state
//        _isDarkModeFlow.value = isDarkMode
//    }
//
//    // Get the current dark mode preference
//    private fun getDarkModePreference(): Boolean {
//        return sharedPreferences.getBoolean(DARK_MODE_KEY, false)
//    }
//}