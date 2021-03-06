package com.pepdoesthings.marvelchars.domain

import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth
import com.pepdoesthings.marvelchars.data.network.MarvelService
import com.pepdoesthings.marvelchars.data.network.RetrofitModule
import com.pepdoesthings.marvelchars.data.repository.MarvelRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import timber.log.Timber
import kotlin.system.measureTimeMillis

class GetMarvelCharacterDetailUseCaseTest {
    private lateinit var getMarvelCharacterDetailUseCase: GetMarvelCharacterDetailUseCase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        getMarvelCharacterDetailUseCase =
            GetMarvelCharacterDetailUseCase(
                MarvelRepositoryImpl(
                    MarvelService(
                        context,
                        RetrofitModule.provideMarvelApiClient(RetrofitModule.getRetrofit())
                    )
                )
            )
    }

    @Test
    fun getMarvelCharacterDetailFromApiTest() = runBlocking {
        val totalExecutionTime = measureTimeMillis {
            val character = getMarvelCharacterDetailUseCase(1009148)

            Truth.assertThat(character.allCharacters.isNotEmpty())
        }
        Timber.d("getMarvelCharactersFromApiTest(): Total Execution Time: $totalExecutionTime")
    }
}