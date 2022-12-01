package practice.effective.MarvelHero.network

import practice.effective.MarvelHero.network.dtos.ErrorDto

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val value: T) : ResponseWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorDto? = null) :
        ResponseWrapper<Nothing>()

    object NetworkError : ResponseWrapper<Nothing>()
}
