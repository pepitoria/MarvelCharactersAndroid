package com.pepdoesthings.marvelchars.domain

import com.pepdoesthings.marvelchars.data.MarvelRepository
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacters
import javax.inject.Inject

class GetMarvelCharactersUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    suspend operator fun invoke() = MarvelCharacters.mapFrom(repository.getCharacters())
}