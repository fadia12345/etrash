package com.dicoding.etrash.di


import android.content.Context
import com.dicoding.etrash.api.ApiConfig
import com.dicoding.etrash.pref.UserPreference
import com.dicoding.etrash.pref.dataStore
import com.dicoding.etrash.repository.UserRepository

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context)
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(pref, apiService)
    }
}