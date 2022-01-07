package com.pepdoesthings.marvelchars.data.network

import android.content.Context
import com.pepdoesthings.marvelchars.BuildConfig
import com.pepdoesthings.marvelchars.data.model.CharactersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest
import javax.inject.Inject
import android.net.NetworkInfo

import android.net.ConnectivityManager
import com.pepdoesthings.marvelchars.data.model.MarvelError
import dagger.hilt.android.qualifiers.ApplicationContext


class MarvelService @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val api: MarvelApiClient
) {

    companion object {
        const val PAGE_SIZE = 100
    }

    suspend fun getCharacterDetail(charId: Long): CharactersResponse? {

        if (!isConnected()) {
            CharactersResponse.getWithError(-1, "No Internet Connection")
        }

        return withContext(Dispatchers.IO) {

            val timestamp = getTimestamp()
            val hash = getHash(timestamp)

            val response =
                api.getCharacterById(charId, BuildConfig.MARVEL_CLIENT_ID, timestamp, hash)

            if (response.isSuccessful) {
                response.body()
            } else {
                CharactersResponse.getWithError(response.code(), response.message())
            }
        }
    }

    suspend fun getCharacters(search: String): CharactersResponse? {
        if (!isConnected()) {
            return CharactersResponse.getWithError(-1, "No Internet Connection")
        }

        return withContext(Dispatchers.IO) {
            val timestamp = getTimestamp()
            val hash = getHash(timestamp)

            val response: Response<CharactersResponse>

            if (search.trim().isEmpty()) {
                // No search
                response =
                    api.getCharacters(
                        BuildConfig.MARVEL_CLIENT_ID,
                        timestamp,
                        hash,
                        PAGE_SIZE,
                        // we will be using offset param for pagination, for now, just 0 to retrieve the first page.
                        0
                    )
            } else {
                // search
                response =
                    api.getCharacters(
                        BuildConfig.MARVEL_CLIENT_ID,
                        timestamp,
                        hash,
                        PAGE_SIZE,
                        // we will be using offset param for pagination, for now, just 0 to retrieve the first page.
                        0,
                        search
                    )
            }

            if (response.isSuccessful) {
                response.body()
            } else {
                CharactersResponse.getWithError(response.code(), response.message())
            }

        }
    }

    fun isConnected(): Boolean {
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    /*
    Authentication for Server-Side Applications

    Server-side applications must pass two parameters in addition to the apikey parameter:
        ts - a timestamp (or other long string which can change on a request-by-request basis)
        hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)

    For example, a user with a public key of "1234" and a private key of "abcd" could construct a valid call
    as follows: http://gateway.marvel.com/v1/public/comics?ts=1&apikey=1234&hash=ffd275c5130566a2916217b101f26150 (the hash value is the md5 digest of 1abcd1234)
    */
    fun getTimestamp(): String {
        return System.currentTimeMillis().toString()
    }

    fun getHash(timestamp: String): String {
        return md5(timestamp + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_CLIENT_ID).toHex()
    }

    fun md5(str: String): ByteArray =
        MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))

    fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

}