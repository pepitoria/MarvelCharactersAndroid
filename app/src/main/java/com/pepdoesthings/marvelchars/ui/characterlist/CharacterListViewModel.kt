package com.pepdoesthings.marvelchars.ui.characterlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getChars() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMarvelCharactersUseCase()
            isLoading.postValue(false)
            marvelCharacters.postValue(result)
        }
    }
}