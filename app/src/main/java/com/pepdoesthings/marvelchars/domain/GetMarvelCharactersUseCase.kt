package com.pepdoesthings.marvelchars.domain

import com.pepdoesthings.marvelchars.data.MarvelRepository
import javax.inject.Inject

class GetMarvelCharactersUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    suspend operator fun invoke() = repository.getCharacters()
}