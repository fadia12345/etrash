package com.dicoding.etrash.ui.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dicoding.etrash.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    var email by mutableStateOf("")

    var password by mutableStateOf("")

    // State for error messages
    var emailError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    // State for button enabled
    //var isButtonEnabled: Boolean by mutableStateOf(false)

    fun validateEmail() {
        // Validate email format
        emailError = if (isValidEmail(email)) null else "Invalid email format"
        //updateButtonState()
    }

    fun validatePassword() {
        // Validate password length
        passwordError = if (password.length >= 8) null else "Password must be at least 8 characters"
        //updateButtonState()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}