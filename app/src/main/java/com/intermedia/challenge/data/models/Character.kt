package com.intermedia.challenge.data.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character(
    val comics: Appearances = Appearances() ,
    val description: String = "",
    val events: Appearances = Appearances(),
    val id: Int = 0,
    val name: String = "",
    val series: Appearances = Appearances(),
    val stories: Appearances = Appearances(),
    val thumbnail: Thumbnail = Thumbnail(),
    val urls: List<Url> = listOf()
)

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)

data class Appearances(
    val available: Int = 0,
    val collectionURI: String = "",
    @SerializedName("items")
    val appearances: List<Appearance> = listOf(),
    val returned: Int = 0
)

data class Appearance(
    val name: String = "",
    val resourceURI: String = "",
    val type: String = ""
)

//used in event
data class Thumbnail(
    val extension: String = "",
    val path: String = ""
)

data class Url(
    val type: String = "",
    val url: String = ""
)