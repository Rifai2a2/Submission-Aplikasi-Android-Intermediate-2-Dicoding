package com.dicoding.picodiploma.mystoryapp.signup

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mystoryapp.data.UserRepository


class SignupViewModel(private val repository: UserRepository): ViewModel() {
    fun register(name: String, email: String, password: String) =
        repository.signup(name,email, password)
}