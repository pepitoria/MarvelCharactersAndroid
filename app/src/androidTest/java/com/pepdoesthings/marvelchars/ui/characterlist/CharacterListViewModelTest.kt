package com.pepdoesthings.marvelchars.ui.characterlist

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.pepdoesthings.marvelchars.data.network.MarvelNetworkException
import com.pepdoesthings.marvelchars.data.network.MarvelService
import com.pepdoesthings.marvelchars.data.network.RetrofitModule
import com.pepdoesthings.marvelchars.data.repository.MarvelRepositoryImpl
import com.pepdoesthings.marvelchars.domain.GetMarvelCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterListViewModelTest {

    private val TIMEOUT = 3000L

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CharacterListViewModel

    @Before
    fun setup() {
        viewModel = CharacterListViewModel(
            GetMarvelCharactersUseCase(
                MarvelRepositoryImpl(
                    MarvelService(RetrofitModule.provideMarvelApiClient(RetrofitModule.getRetrofit()))
                )
            )
        )

    }

    @Test
    fun getCharsTest() {
        viewModel.getChars("")
        Thread.sleep(TIMEOUT)

        val marvelChars = viewModel.marvelCharacters.value
        assertThat(marvelChars != null)
        assertThat(marvelChars?.allCharacters?.isNotEmpty())
    }

    @Test
    fun getCharsSearchTestReturnsData() {
        val SEARCH = "iron"
        viewModel.getChars(SEARCH)
        Thread.sleep(TIMEOUT)

        val marvelChars = viewModel.marvelCharacters.value
        assertThat(marvelChars != null)
        assertThat(marvelChars?.allCharacters?.isNotEmpty())
        marvelChars?.allCharacters?.forEach {
            assertThat(it.name.startsWith(SEARCH, ignoreCase = true))
        }
    }

    @Test
    fun getCharsSearchTestReturnsNoException() {
        val SEARCH = "iron"
        viewModel.getChars(SEARCH)
        Thread.sleep(TIMEOUT)
        val marvelChars = viewModel.marvelCharacters.value
        assertThat(marvelChars != null)
    }
}

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher) {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}