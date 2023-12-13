package com.dicoding.picodiploma.mystoryapp.addstory

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mystoryapp.data.UserRepository
import java.io.File

class AddStoryViewModel(private val repository: UserRepository) : ViewModel() {
    fun uploadImage(file: File, description: String) = repository.uploadImage(file, description)
}