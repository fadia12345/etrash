package com.dicoding.etrash.api.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("userProfile")
	val userProfile: UserProfile
)

data class UserProfile(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
