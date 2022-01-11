package com.pepdoesthings.marvelchars.domain

import com.google.common.truth.Truth.assertThat
import com.pepdoesthings.marvelchars.data.network.MarvelService
import com.pepdoesthings.marvelchars.data.network.RetrofitModule
import com.pepdoesthings.marvelchars.data.repository.MarvelRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import timber.log.Timber
import kotlin.system.measureTimeMillis
import android.app.Application
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.platform.app.InstrumentationRegistry


class GetMarvelCharactersUseCaseTest {
    private lateinit var getMarvelCharactersUseCase: GetMarvelCharactersUseCase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        getMarvelCharactersUseCase =
            GetMarvelCharactersUseCase(
                MarvelRepositoryImpl(
                    MarvelService(
                        context,
                        RetrofitModule.provideMarvelApiClient(RetrofitModule.getRetrofit())
                    )
                )
            )

    }

    @Test
    fun getMarvelCharactersFromApiTest() = runBlocking {
        val totalExecutionTime = measureTimeMillis {
            val marvelChars = getMarvelCharactersUseCase("")

            assertThat(marvelChars.allCharacters.isNotEmpty())
        }
        Timber.d("getMarvelCharactersFromApiTest(): Total Execution Time: $totalExecutionTime")
    }

    @Test
    fun getMarvelCharactersFromApiWithSearchReturnsData() = runBlocking {
        val SEARCH = "iron"
        val totalExecutionTime = measureTimeMillis {
            val marvelChars = getMarvelCharactersUseCase(SEARCH)

            assertThat(marvelChars.allCharacters.isNotEmpty())
            marvelChars.allCharacters.forEach {
                assertThat(it.name.startsWith(SEARCH, ignoreCase = true))
            }
        }
        Timber.d("getMarvelCharactersFromApiTest(): Total Execution Time: $totalExecutionTime")
    }

    @Test
    fun getMarvelCharactersFromApiWithSearchReturnsNoException() = runBlocking {
        val SEARCH = "iron"
        val totalExecutionTime = measureTimeMillis {
            val marvelChars = getMarvelCharactersUseCase(SEARCH)

            assertThat(marvelChars != null)
        }
        Timber.d("getMarvelCharactersFromApiWithSearchReturnsNoException(): Total Execution Time: $totalExecutionTime")
    }
}
