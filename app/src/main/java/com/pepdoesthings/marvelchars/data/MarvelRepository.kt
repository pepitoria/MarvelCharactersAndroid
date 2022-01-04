package com.pepdoesthings.marvelchars.data

import com.pepdoesthings.marvelchars.data.model.CharactersResponse
import com.pepdoesthings.marvelchars.data.network.MarvelService
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val api: MarvelService
) {
    suspend fun getCharacters(): CharactersResponse? {
        return api.getCharacters()
    }

    suspend fun getCharacterDetail(charId: Long): CharactersResponse? {
        return api.getCharacterDetail(charId)
    }
}