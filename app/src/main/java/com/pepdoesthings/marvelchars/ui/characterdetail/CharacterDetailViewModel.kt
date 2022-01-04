package com.pepdoesthings.marvelchars.ui.characterdetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepdoesthings.marvelchars.domain.GetMarvelCharacterDetailUseCase
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getMarvelCharacterDetailUseCase: GetMarvelCharacterDetailUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val marvelCharacter = MutableLiveData<MarvelCharacters>()

    fun getChar(charId: Long) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMarvelCharacterDetailUseCase(charId)
            isLoading.postValue(false)
            result.let {
                marvelCharacter.postValue(it)
            }

        }
    }

    fun openURL(url: String, context: Context?) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        context?.startActivity(i)
    }
}