package com.dicoding.etrash.api

import com.dicoding.etrash.api.response.LoginResponse
import com.dicoding.etrash.api.response.ProfileResponse
import com.dicoding.etrash.api.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("profile")
    suspend fun Profile(
        @Header("Authorization") token: String
    ): ProfileResponse



}