package com.example.marvelchallenge.vo

import java.lang.Exception

/**
 * Sealed class adonde se alojan las clases que retornan un objeto de valor (value object)
 */

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val exception: Exception) : Resource<T>()
}
