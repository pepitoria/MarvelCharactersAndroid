package com.pepdoesthings.marvelchars.data.network

class MarvelNetworkException constructor(
    val code: Int,
    val msg: String
) : Exception() {

}