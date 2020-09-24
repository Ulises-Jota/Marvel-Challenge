package com.example.marvelchallenge.domain

import com.example.marvelchallenge.data.model.Evento
import com.example.marvelchallenge.data.model.Personaje
import com.example.marvelchallenge.vo.Resource

interface Repo {
    suspend fun getPersonajesList(): Resource<List<Personaje>>

    suspend fun getEventosList(): Resource<List<Evento>>
}