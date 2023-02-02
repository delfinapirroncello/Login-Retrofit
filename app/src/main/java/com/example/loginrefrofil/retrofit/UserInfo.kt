package com.example.loginrefrofil.retrofit

import com.google.gson.annotations.SerializedName

class UserInfo (
    @SerializedName(com.example.loginrefrofil.Constants.EMAIL_PARAM) val email: String,
    @SerializedName(com.example.loginrefrofil.Constants.PASSWORD_PARAM) val pass: String
)