package com.pepdoesthings.marvelchars.ui.characterdetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepdoesthings.marvelchars.data.network.MarvelNetworkException
import com.pepdoesthings.marvelchars.domain.GetMarvelCharacterDetailUseCase
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getMarvelCharacterDetailUseCase: GetMarvelCharacterDetailUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val marvelCharacter = MutableLiveData<MarvelCharacters>()
    val apiError = MutableLiveData<MarvelNetworkException>()

    fun getChar(charId: Long) {
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
                val result = getMarvelCharacterDetailUseCase(charId)
                isLoading.postValue(false)
                result.let {
                    marvelCharacter.postValue(it)
                }
            } catch (mEx: MarvelNetworkException) {
                Timber.e("MarvelNetworkException(${mEx.code}, ${mEx.msg})")
                isLoading.postValue(false)
                apiError.postValue(mEx)
            }

        }
    }

    fun openURL(url: String, context: Context?) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        context?.startActivity(i)
    }
}