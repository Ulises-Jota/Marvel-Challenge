package com.example.marvelchallenge.domain

import com.example.marvelchallenge.data.model.CharacterResultResponse
import com.example.marvelchallenge.data.model.EventDataWrapperResponse
import retrofit2.http.GET

private const val API_KEY = "?apikey=EJEMPLO1234567890"

interface WebService {
    @GET("characters$API_KEY")
    suspend fun getCharacterResultResponse(): CharacterResultResponse

    @GET("events$API_KEY")
    suspend fun getEventDataWrapperResponse(): EventDataWrapperResponse
}

