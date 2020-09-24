package com.example.marvelchallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.marvelchallenge.domain.Repo
import com.example.marvelchallenge.vo.Resource
import kotlinx.coroutines.Dispatchers

class EventosViewModel(private val repo: Repo) : ViewModel() {

    val fetchEventosList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getEventosList())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}
