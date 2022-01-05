package com.pepdoesthings.marvelchars.data

import com.google.gson.Gson
import com.pepdoesthings.marvelchars.data.model.CharactersResponse
import com.pepdoesthings.marvelchars.data.repository.MarvelRepository
import java.nio.charset.Charset

class FakeMarvelRepository : MarvelRepository {
    override suspend fun getCharacters(search: String): CharactersResponse? {
        return Gson().fromJson(
            readJsonFromAssets("getChars.json"),
            CharactersResponse::class.java
        )
    }

    override suspend fun getCharacterDetail(charId: Long): CharactersResponse? {
        return Gson().fromJson(
            readJsonFromAssets("getCharById.json"),
            CharactersResponse::class.java
        )
    }

    fun readJsonFromAssets(fileName: String): String? {
        return this::class.java.classLoader.getResource(fileName).readText(Charset.defaultCharset())
    }
}
