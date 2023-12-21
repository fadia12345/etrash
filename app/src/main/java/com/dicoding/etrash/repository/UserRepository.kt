package com.dicoding.etrash.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.dicoding.etrash.api.ApiService
import com.dicoding.etrash.api.response.LoginResponse
import com.dicoding.etrash.api.response.RegisterResponse
import com.dicoding.etrash.pref.HelperStat
import com.dicoding.etrash.pref.UserModel
import com.dicoding.etrash.pref.UserPreference
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

import retrofit2.HttpException

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<HelperStat<UserModel>> {
        return userPreference.getSession().map {
            HelperStat.Success(it)
        }.catch {
//            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)e ->
//            emit(HelperStat.Error(errorResponse.message ?: "Unknown error"))
        }
    }

//    suspend fun logout() {
//        userPreference.logout()
//    }

    fun register(name: String, email: String, password: String): LiveData<HelperStat<RegisterResponse>> = liveData {
        emit(HelperStat.Loading)
        try {
            val successResponse = apiService.register(name, email, password)
            emit(HelperStat.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
            emit(HelperStat.Error(errorResponse.message ?: "Unknown error"))
        }
    }

    fun login(email: String, password: String): LiveData<HelperStat<LoginResponse>> = liveData {
        try {
            val result = apiService.login(email, password)
            emit(HelperStat.Success(result))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(HelperStat.Error(errorResponse.message ?: "Unknown error"))
        } catch (e: Exception) {
            emit(HelperStat.Error("An error occurred"))
        }
    }

    private val _logoutStatus = MutableLiveData<Boolean>()
    val logoutStatus: LiveData<Boolean>
        get() = _logoutStatus


    suspend fun logout() {
        try {

            userPreference.logout()
            _logoutStatus.postValue(true)
        } catch (e: Exception) {
            _logoutStatus.postValue(false)
        }
    }

    private val _userProfile = MutableLiveData<HelperStat<UserModel>>()
    val userProfile: LiveData<HelperStat<UserModel>>
        get() = _userProfile


    suspend fun fetchUserProfile() {
        try {

            val userProfile = userPreference.getSession().first()
            _userProfile.postValue(HelperStat.Success(userProfile))
        } catch (e: Exception) {
            _userProfile.postValue(HelperStat.Error("Failed to fetch user profile"))
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userPreference: UserPreference, apiService: ApiService): UserRepository {
            return instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
        }
    }
}