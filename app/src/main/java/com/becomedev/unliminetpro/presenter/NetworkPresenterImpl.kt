package com.becomedev.unliminetpro.presenter

import android.content.Context
import com.becomedev.unliminetpro.model.NetworkData
import com.google.gson.Gson

class NetworkPresenterImpl(val mainView: NetworkContract.MainView) : NetworkContract.Presenter {

    override fun onLoadJsonDataFinish(networkList: ArrayList<NetworkData.SubNetworkData>) {
        mainView.setDataToRecyclerView(networkList)
    }
}