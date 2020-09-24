package com.example.marvelchallenge.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResultResponse(
    @SerializedName("data") val dataResponse: CharacterDataContainerResponse
)

data class CharacterDataContainerResponse(
    @SerializedName("results") val results: List<Personaje>
)

data class Personaje(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnailResponse: PersonajeThumbnailResponse,
    @SerializedName("comics") val comicList: CharacterComicList
)

data class CharacterComicList(
    @SerializedName("items") val items: List<CharacterComicSummary>
)

data class CharacterComicSummary(
    @SerializedName("name") val name: String? = ""
)

data class PersonajeThumbnailResponse(val path: String, val extension: String)

