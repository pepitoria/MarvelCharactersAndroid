package com.pepdoesthings.marvelchars.domain.model

import android.os.Parcelable
import com.pepdoesthings.marvelchars.data.model.CharactersResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelCharacters(
    val allCharacters: List<MarvelCharacter>
) : Parcelable {

    companion object {
        const val KEY = "com.pepdoesthings.marvelchars.domain.model.MarvelCharacters"

        fun mapFrom(charactersResponse: CharactersResponse?): MarvelCharacters {

            val list: MutableList<MarvelCharacter> = mutableListOf()


            charactersResponse?.data?.results?.forEach { char ->
                val mappedChar = MarvelCharacter(
                    id = char.id,
                    name = char.name,
                    description = char.description,
                    thumbnail = char.thumbnail.getSquaredThumbnail(),
                    image = char.thumbnail.getSquaredImage(),
                    knowMoreUrl = char.getKnowMoreUrl(),
                    comicsUrl = char.getComicsUrl()
                )

                list.add(mappedChar)
            }

            return MarvelCharacters(list)

        }
    }
}

@Parcelize
data class MarvelCharacter(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: String,
    val image: String,
    val knowMoreUrl: String,
    val comicsUrl: String
) : Parcelable
