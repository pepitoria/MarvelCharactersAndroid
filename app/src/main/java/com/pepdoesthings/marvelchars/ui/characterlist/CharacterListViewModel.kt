package com.pepdoesthings.marvelchars.ui.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepdoesthings.marvelchars.domain.GetMarvelCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getMarvelCharactersUseCase: GetMarvelCharactersUseCase
) : ViewModel() {

//    fun onCreate() {
//        viewModelScope.launch {
//            val result = getMarvelCharactersUseCase()
//            Timber.d("result: $result")
//        }
//    }

    fun getChars() {
        viewModelScope.launch {
            val result = getMarvelCharactersUseCase()
            Timber.d("result: $result")
        }
    }
}