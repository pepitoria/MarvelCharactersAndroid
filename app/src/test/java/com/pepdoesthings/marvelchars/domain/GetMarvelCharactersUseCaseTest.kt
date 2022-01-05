package com.pepdoesthings.marvelchars.domain

import com.google.common.truth.Truth.assertThat
import com.pepdoesthings.marvelchars.data.FakeMarvelRepository
import com.pepdoesthings.marvelchars.data.model.CharactersResponse
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacters
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMarvelCharactersUseCaseTest {

    private lateinit var getMarvelCharactersUseCase: GetMarvelCharactersUseCase
    private lateinit var fakeRepository: FakeMarvelRepository
    private var charactersResponse: CharactersResponse? = null
    private lateinit var marvelCharacters: MarvelCharacters

    @Before
    fun setUp() {
        fakeRepository = FakeMarvelRepository()
        getMarvelCharactersUseCase = GetMarvelCharactersUseCase(fakeRepository)

        runBlocking {
            charactersResponse = fakeRepository.getCharacters("")
            marvelCharacters = getMarvelCharactersUseCase("")
        }
    }

    @Test
    fun `MarvelCharacters mapper, mapping a complete object, list of characters`() {
        assertThat(charactersResponse?.data?.results?.size == marvelCharacters.allCharacters.size)
    }

    @Test
    fun `MarvelCharacters mapper, mapping a complete object, character id`() {
        charactersResponse?.data?.results?.forEachIndexed { index, result ->
            assertThat(result.id == marvelCharacters.allCharacters[index].id)
        }
    }

    @Test
    fun `MarvelCharacters mapper, mapping a complete object, character name`() {
        charactersResponse?.data?.results?.forEachIndexed { index, result ->
            assertThat(result.name == marvelCharacters.allCharacters[index].name)
        }
    }

    @Test
    fun `MarvelCharacters mapper, mapping a complete object, character description`() {
        charactersResponse?.data?.results?.forEachIndexed { index, result ->
            assertThat(result.description == marvelCharacters.allCharacters[index].description)
        }
    }

    @Test
    fun `MarvelCharacters mapper, mapping a complete object, character thumbnail`() {
        charactersResponse?.data?.results?.forEachIndexed { index, result ->
            assertThat(result.thumbnail.getSquaredImage() == marvelCharacters.allCharacters[index].thumbnail)
        }
    }

    @Test
    fun `MarvelCharacters mapper, mapping a complete object, character image`() {
        charactersResponse?.data?.results?.forEachIndexed { index, result ->
            assertThat(result.thumbnail.getSquaredImage() == marvelCharacters.allCharacters[index].image)
        }
    }

    @Test
    fun `MarvelCharacters mapper, mapping a complete object, character knowMoreUrl`() {
        charactersResponse?.data?.results?.forEachIndexed { index, result ->
            assertThat(result.getKnowMoreUrl() == marvelCharacters.allCharacters[index].knowMoreUrl)
        }
    }

    @Test
    fun `MarvelCharacters mapper, mapping a complete object, character comicsUrl`() {
        charactersResponse?.data?.results?.forEachIndexed { index, result ->
            assertThat(result.getComicsUrl() == marvelCharacters.allCharacters[index].comicsUrl)
        }
    }
}