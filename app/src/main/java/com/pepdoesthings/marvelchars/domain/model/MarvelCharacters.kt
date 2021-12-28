package com.pepdoesthings.marvelchars.domain.model

import com.pepdoesthings.marvelchars.data.model.CharactersResponse

data class MarvelCharacters(
    val allCharacters: List<MarvelCharacter>
) {

    companion object {

        fun mapFrom(charactersResponse: CharactersResponse?): MarvelCharacters {

            val list: MutableList<MarvelCharacter> = mutableListOf()


            charactersResponse?.data?.results?.forEach { char ->
                val mappedChar = MarvelCharacter(
                    id = char.id,
                    name = char.name,
                    description = char.description,
                    thumbnail = char.thumbnail.getSquaredThumbnail()
                )

                list.add(mappedChar)
            }

            return MarvelCharacters(list)

        }
    }
}

data class MarvelCharacter(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: String
)
