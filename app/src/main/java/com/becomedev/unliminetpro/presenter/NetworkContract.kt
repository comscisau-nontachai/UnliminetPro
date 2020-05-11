package com.becomedev.unliminetpro.presenter

import com.becomedev.unliminetpro.model.NetworkData

interface NetworkContract {

    interface MainView{
        fun setDataToRecyclerView(networkList:ArrayList<NetworkData.SubNetworkData>)
    }
    interface Presenter{
        fun onLoadJsonDataFinish(networkList: ArrayList<NetworkData.SubNetworkData>)
    }
}