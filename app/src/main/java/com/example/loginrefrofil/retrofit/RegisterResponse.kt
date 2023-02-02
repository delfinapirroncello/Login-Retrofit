package com.example.loginrefrofil.retrofit

data class RegisterResponse(var token: String, var id: String) : SuccessResponse(token)
