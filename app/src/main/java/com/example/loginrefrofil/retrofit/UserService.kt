package com.example.loginrefrofil.retrofit

import com.example.loginrefrofil.Constants
import retrofit2.http.GET

interface UserService {
    @GET(Constants.API_PATH + Constants.USERS_PATH + Constants.TWO_PATH)
    suspend fun getSingerUser() : SingerUserResponse
}