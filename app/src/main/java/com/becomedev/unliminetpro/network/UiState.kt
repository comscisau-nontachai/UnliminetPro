package com.becomedev.unliminetpro.network

sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    data class Success<T : Any>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
