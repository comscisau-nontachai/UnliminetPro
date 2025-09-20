package com.becomedev.unliminetpro.network

sealed class ResourceWrapper <out T>{
    data class Success<out T>(val result : T) : ResourceWrapper<T>()
    data class Failure(val message: String?) : ResourceWrapper<Nothing>()
    data class NetworkError(val title: String?, val message: String?): ResourceWrapper<Nothing>()
    object Loading : ResourceWrapper<Nothing>()
}