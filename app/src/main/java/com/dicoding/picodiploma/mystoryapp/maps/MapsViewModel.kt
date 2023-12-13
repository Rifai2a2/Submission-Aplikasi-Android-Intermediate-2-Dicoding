package com.dicoding.picodiploma.mystoryapp.maps

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.mystoryapp.data.UserRepository

class MapsViewModel(private val repository: UserRepository) : ViewModel() {

    fun getStoriesWithLocation() = repository.getStoriesWithLocation()
}