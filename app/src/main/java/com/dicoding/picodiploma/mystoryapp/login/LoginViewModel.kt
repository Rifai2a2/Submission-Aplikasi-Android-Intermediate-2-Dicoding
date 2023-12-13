package com.dicoding.picodiploma.mystoryapp.login

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mystoryapp.data.UserRepository
import com.dicoding.picodiploma.mystoryapp.userpref.UserModel

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    fun login(email: String, password: String) = repository.login(email,password)

    suspend fun saveSession(userModel: UserModel){
        repository.saveSession(userModel)
    }
}