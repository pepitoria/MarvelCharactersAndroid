package com.pepdoesthings.marvelchars.data.repository

import com.pepdoesthings.marvelchars.data.model.CharactersResponse
import com.pepdoesthings.marvelchars.data.network.MarvelService
import javax.inject.Inject

interface MarvelRepository {
    suspend fun getCharacters(search: String): CharactersResponse?
    suspend fun getCharacterDetail(charId: Long): CharactersResponse?
}

