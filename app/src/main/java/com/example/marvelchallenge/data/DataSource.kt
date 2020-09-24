package com.example.marvelchallenge.data

import com.example.marvelchallenge.data.model.Evento
import com.example.marvelchallenge.data.model.Personaje
import com.example.marvelchallenge.vo.Resource
import com.example.marvelchallenge.vo.RetrofitClient

class DataSource {
    // Busca la información en el server
    suspend fun getPersonajesMarvel(): Resource<List<Personaje>> {
        val personajesContainer = RetrofitClient.webService.getCharacterResultResponse()
        return Resource.Success(personajesContainer.dataResponse.results)
    }

    suspend fun getEventosMarvel(): Resource<List<Evento>> {
        val eventosContainer = RetrofitClient.webService.getEventDataWrapperResponse()
        return Resource.Success(eventosContainer.data.results)
    }
}

