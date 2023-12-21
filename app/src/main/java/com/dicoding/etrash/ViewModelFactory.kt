package com.dicoding.etrash

import androidx.lifecycle.ViewModel
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.dicoding.etrash.di.Injection
import com.dicoding.etrash.ui.screen.login.LoginViewModel
import com.dicoding.etrash.ui.screen.profile.ProfileViewModel
import com.dicoding.etrash.ui.screen.register.RegisterViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(Injection.provideUserRepository(context)) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(Injection.provideUserRepository(context)) as T
            }
//            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
//                ProfileViewModel() as T
//            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}