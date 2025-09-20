package com.becomedev.unliminetpro.repository

import com.becomedev.unliminetpro.data.model.PromotionModel
import com.becomedev.unliminetpro.network.ApiService
import com.becomedev.unliminetpro.network.UiState

class PackageRepository(private val api: ApiService) {

    suspend fun getPackageTrueMove(): UiState<PromotionModel> {
        return try {
            val response = api.getTrueMovePackage()
            response.body()?.let {
                UiState.Success(it)
            } ?: UiState.Error("Empty response body")

        } catch (e: Exception) {
            UiState.Error(e.message ?: "An unknown error occurred")
        }
    }

}