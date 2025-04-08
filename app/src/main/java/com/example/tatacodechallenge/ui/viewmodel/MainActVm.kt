package com.example.tatacodechallenge.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tatacodechallenge.data.RandomText
import com.example.tatacodechallenge.repository.RandomStringRepository

class RandomStringViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RandomStringRepository(application)

    private val _randomStrings = MutableLiveData<MutableList<RandomText>>(mutableListOf())
    val randomStrings: LiveData<MutableList<RandomText>> = _randomStrings

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchRandomString(length: Int) {
        val result = repository.getRandomString(length)
        if (result != null) {
            _randomStrings.value?.add(0, result)
            _randomStrings.postValue(_randomStrings.value)
        } else {
            _error.postValue("Failed to fetch data from content provider.")
        }
    }

    fun deleteAll() {
        _randomStrings.postValue(mutableListOf())
    }

    fun deleteItem(index: Int) {
        _randomStrings.value?.let {
            it.removeAt(index)
            _randomStrings.postValue(it)
        }
    }
}