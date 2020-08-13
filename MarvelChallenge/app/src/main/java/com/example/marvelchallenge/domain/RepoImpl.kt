package com.example.marvelchallenge.domain

import com.example.marvelchallenge.data.DataSource
import com.example.marvelchallenge.data.model.Evento
import com.example.marvelchallenge.data.model.Personaje
import com.example.marvelchallenge.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {
    override suspend fun getPersonajesList(): Resource<List<Personaje>> {
        return dataSource.getPersonajesMarvel()
    }

    override suspend fun getEventosList(): Resource<List<Evento>> {
        return dataSource.getEventosMarvel()
    }
}


