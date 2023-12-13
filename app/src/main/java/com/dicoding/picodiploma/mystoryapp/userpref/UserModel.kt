package com.dicoding.picodiploma.mystoryapp.userpref

data class UserModel (
    val token: String,
    val email: String,
    val password: String,
    val isLogin: Boolean = false,
)

