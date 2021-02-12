package com.intermedia.challenge.data.models

import androidx.annotation.NonNull
import androidx.room.Query
import com.google.gson.annotations.SerializedName

data class Event(
    val characters: Characters,
    val comics: Appearances = Appearances() ,
    val creators: Creators,
    val description: String,
    val end: String,
    val id: Int,
    val modified: String,
    val next: Next,
    val previous: Previous,
    val resourceURI: String,
    val series: Series, //
    val start: String,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val title: String,
    val urls: List<Url>
)

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)
data class Item(
    val name: String,
    val resourceURI: String
)

data class Next(
    val name: String,
    val resourceURI: String
)
data class Previous(
    val name: String,
    val resourceURI: String
)
data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)
data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)