package com.becomedev.unliminetpro.presenter

import com.becomedev.unliminetpro.model.NetworkData

interface NetworkContract {

    interface FragmentView{
        fun setDataToRecyclerView(networkList:ArrayList<NetworkData.SubNetworkData>)
    }
    interface Presenter{
        fun onLoadJsonDataFinish(networkList: ArrayList<NetworkData.SubNetworkData>)
    }
    interface ItemClick{
        fun onItemClick(str:String)
    }
}