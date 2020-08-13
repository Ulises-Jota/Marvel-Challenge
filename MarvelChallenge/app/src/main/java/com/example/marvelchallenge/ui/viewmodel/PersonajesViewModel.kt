package com.example.marvelchallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.marvelchallenge.domain.Repo
import com.example.marvelchallenge.vo.Resource
import kotlinx.coroutines.Dispatchers

class PersonajesViewModel(private val repo: Repo) : ViewModel() {

    val fetchPersonajesList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getPersonajesList())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}
