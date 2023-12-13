package com.dicoding.picodiploma.mystoryapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.picodiploma.mystoryapp.data.UserRepository
import com.dicoding.picodiploma.mystoryapp.response.ListStoryItem
import com.dicoding.picodiploma.mystoryapp.userpref.UserModel

class MainViewModel(private val repository: UserRepository): ViewModel() {
    fun getStories() = repository.getStory()

    val quote: LiveData<PagingData<ListStoryItem>> =
        repository.getQuote().cachedIn(viewModelScope)

    suspend fun logout(){
        repository.logout()
    }
    fun getSession(): LiveData<UserModel>{
        return repository.getSession().asLiveData()
    }
}