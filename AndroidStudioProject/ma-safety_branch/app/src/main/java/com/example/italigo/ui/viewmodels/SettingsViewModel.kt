//package com.example.italigo.ui.viewmodels
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.italigo.data.PreferencesManager
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class SettingsViewModel @Inject constructor (private val preferencesManager: PreferencesManager) : ViewModel() {
//
//    // Observe the theme preference
//    val isDarkMode: Flow<Boolean> = preferencesManager.isDarkModeFlow
//
//    // Method to update the dark mode preference
//    suspend fun setDarkMode(isDarkMode: Boolean) {
//        preferencesManager.setDarkMode(isDarkMode)
//    }
//
////    // Save the theme preference
////    fun setDarkMode(isDarkMode: Boolean) {
////        viewModelScope.launch {
////            preferencesManager.setDarkMode(isDarkMode)
////        }
////    }
//}