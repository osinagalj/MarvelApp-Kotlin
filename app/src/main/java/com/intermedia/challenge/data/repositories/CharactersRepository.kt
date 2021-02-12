package com.intermedia.challenge.data.repositories

import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.services.CharacterService
import com.intermedia.challenge.data.services.CharactersResponse

class CharactersRepository(
    private val characterService: CharacterService,
): BaseRepository() {

    suspend fun getCharacters(offset: Int, limit: Int = 15): NetResult<CharactersResponse> =
        handleResult(characterService.getCharacters(authParams.getMap(), offset, limit))

    suspend fun getCharacterById(id : String): NetResult<CharactersResponse> =
        handleResult(characterService.getCharacterById(id,authParams.getMap()))


}