package com.intermedia.challenge.ui.characterdDetails


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intermedia.challenge.data.models.Appearance
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.repositories.CharactersRepository
import kotlinx.coroutines.launch


class CharacterDetailsViewModel(private val charactersRepository: CharactersRepository) : ViewModel(){

    private val _comics = MutableLiveData<List<Appearance>>()
    val comics: LiveData<List<Appearance>> get() = _comics

    val character = MutableLiveData<Character>()



    fun loadCharacter(id: String): LiveData<Character> {

        viewModelScope.launch {
            when (val response = charactersRepository.getCharacterById(id)) {
                is NetResult.Success -> {
                    character.postValue(response.data.charactersList.characters[0])
                    _comics.postValue(response.data.charactersList.characters[0].comics.appearances)
                }
                is NetResult.Error -> {
                    System.out.println("Error accediendo al character por id")
                }
            }
        }

        return character
    }


}

