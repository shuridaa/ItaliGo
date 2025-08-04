//package com.example.italigo.di
//
//import android.content.Context
//import com.example.italigo.data.PreferencesManager
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager {
//        return PreferencesManager(context)
//    }
//}