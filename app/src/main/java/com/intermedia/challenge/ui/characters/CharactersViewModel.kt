package com.intermedia.challenge.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intermedia.challenge.data.models.*
import com.intermedia.challenge.data.repositories.CharactersRepository
import kotlinx.coroutines.launch


class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters
    var pos: Int = 0


    init {
        loadCharacters(0)
    }

    private fun loadCharacters(offset: Int) {

        viewModelScope.launch {
            when (val response = charactersRepository.getCharacters(offset)) {
                is NetResult.Success -> {
                    _characters.postValue(response.data.charactersList.characters)
                    pos = offset
                }
                is NetResult.Error -> {
                    println("Error getting the data from the API using the service >> getCharacters() <<")

                }
            }
        }
    }

    fun loadMoreCharacters() {
        // TODO complete
        pos += 15
        viewModelScope.launch {
            when (val response = charactersRepository.getCharacters(pos)) {
                is NetResult.Success -> {
                    _characters.postValue(response.data.charactersList.characters)
                }
            }
        }
    }
}