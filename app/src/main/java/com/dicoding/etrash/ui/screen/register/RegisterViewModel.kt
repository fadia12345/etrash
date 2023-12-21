package com.dicoding.etrash.ui.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.etrash.api.response.RegisterResponse
import com.dicoding.etrash.pref.HelperStat
import com.dicoding.etrash.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    var fullName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    private val _registrationStatus = MutableLiveData<HelperStat<RegisterResponse>>()
    val registrationStatus: LiveData<HelperStat<RegisterResponse>> = _registrationStatus

    fun register() {
        // Lakukan validasi data (jika diperlukan)

        // Panggil repository untuk melakukan registrasi
        viewModelScope.launch {
            _registrationStatus.value = HelperStat.Loading
            _registrationStatus.value = userRepository.register(fullName, email, password).value
        }
    }
}