package com.example.marvelchallenge.data.model

import com.google.gson.annotations.SerializedName

data class EventDataWrapperResponse(
    @SerializedName("data") val data: EventDataContainerResponse
)

data class EventDataContainerResponse(
    @SerializedName("results") val results: List<Evento>
)

data class Evento(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnailResponse: EventoThumbnailResponse,
    @SerializedName("start") val dateStart: String? = "",
    @SerializedName("end") val dateEnd: String? = "",
    @SerializedName("comics") val comicList: ComicList
)

data class ComicList(
    @SerializedName("items") val items: List<ComicSummary>
)

data class ComicSummary(
    @SerializedName("name") val name: String? = ""
)

data class EventoThumbnailResponse(val path: String, val extension: String)