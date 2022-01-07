package com.pepdoesthings.marvelchars.ui.characterlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepdoesthings.marvelchars.data.model.MarvelError
import com.pepdoesthings.marvelchars.domain.GetMarvelCharactersUseCase
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getMarvelCharactersUseCase: GetMarvelCharactersUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val marvelCharacters = MutableLiveData<MarvelCharacters>()
    val apiError = MutableLiveData<MarvelError>()

    fun setFirstCall(firstCall: MarvelCharacters?) {
        firstCall?.let {
            if (it.marvelError != null) {
                // there is an error
                apiError.postValue(it.marvelError as MarvelError)
            } else {
                // no error
                marvelCharacters.postValue(it)
            }
        }
    }

    fun getChars(search: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMarvelCharactersUseCase(search)
            isLoading.postValue(false)

            if (result.marvelError != null) {
                // there is an error
                apiError.postValue(result.marvelError as MarvelError)
            } else {
                // no error
                marvelCharacters.postValue(result)
            }
        }
    }
}