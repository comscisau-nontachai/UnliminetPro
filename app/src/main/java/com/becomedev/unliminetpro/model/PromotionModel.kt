package com.becomedev.unliminetpro.model

import com.google.gson.annotations.SerializedName

data class PromotionModel(
    @SerializedName("network")
    val network : List<NetworkPromotionModel>
)

data class NetworkPromotionModel(
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("day") val day: Int,
    @SerializedName("tel") val tel: String,
    @SerializedName("qty_net") val qtyNet: String,
    @SerializedName("speed_net") val speedNet: String,
    @SerializedName("free_call") val freeCall: String = "",
    @SerializedName("free_wifi") val freeWifi: String = ""
)