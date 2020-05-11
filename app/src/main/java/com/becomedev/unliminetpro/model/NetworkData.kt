package com.becomedev.unliminetpro.model

import com.google.gson.annotations.SerializedName

class NetworkData {
    @SerializedName("network")
    lateinit var list: ArrayList<SubNetworkData>

    data class SubNetworkData(
        val name: String,
        val price: Int,
        val day: Int,
        val tel: String,
        val qty_net: String,
        val speed_net: String,
        val free_call: String = "",
        val free_wifi: String = ""
    )
}
