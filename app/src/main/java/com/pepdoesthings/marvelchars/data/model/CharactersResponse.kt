package com.pepdoesthings.marvelchars.data.model

// all these classes were generated using
// https://jsonformatter.org/json-to-kotlin

data class CharactersResponse(
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: Data
)

data class Data(
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<Result>
)

data class Result(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val comics: Comics,
    val series: Comics,
    val stories: Stories,
    val events: Comics,
    val urls: List<URL>
)

data class Comics(
    val available: Long,
    val collectionURI: String,
    val items: List<ComicsItem>,
    val returned: Long
)

data class ComicsItem(
    val resourceURI: String,
    val name: String
)

data class Stories(
    val available: Long,
    val collectionURI: String,
    val items: List<StoriesItem>,
    val returned: Long
)

data class StoriesItem(
    val resourceURI: String,
    val name: String,
    val type: ItemType
)

enum class ItemType(val value: String) {
    Cover("cover"),
    Empty(""),
    InteriorStory("interiorStory");

    companion object {
        public fun fromValue(value: String): ItemType = when (value) {
            "cover" -> Cover
            "" -> Empty
            "interiorStory" -> InteriorStory
            else -> throw IllegalArgumentException()
        }
    }
}

data class Thumbnail(
    val path: String,
    val extension: String
) {
    // https://developer.marvel.com/documentation/images

    fun getSquaredThumbnail(): String {
        // we just hardcode this part.
        // not a great practice, but for the scope of this exercise this will do.
        var url = path + "/standard_medium." + extension
        // we like https.
        url = url.replace("http://", "https://")
        return url
    }
}

data class URL(
    val type: URLType,
    val url: String
)

enum class URLType(val value: String) {
    Comiclink("comiclink"),
    Detail("detail"),
    Wiki("wiki");

    companion object {
        public fun fromValue(value: String): URLType = when (value) {
            "comiclink" -> Comiclink
            "detail" -> Detail
            "wiki" -> Wiki
            else -> throw IllegalArgumentException()
        }
    }
}