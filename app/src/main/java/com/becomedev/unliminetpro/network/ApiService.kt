package com.becomedev.unliminetpro.network

import com.becomedev.unliminetpro.data.model.PromotionModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("comscisau-nontachai/UnliminetPro/master/app/src/main/assets/data_true.json")
    suspend fun getTrueMovePackage(): Response<PromotionModel>

    @GET("comscisau-nontachai/UnliminetPro/master/app/src/main/assets/data_ais.json")
    suspend fun getAisPackage(): Response<PromotionModel>

    @GET("comscisau-nontachai/UnliminetPro/master/app/src/main/assets/data_dtac.json")
    suspend fun getDtacPackage(): Response<PromotionModel>
}