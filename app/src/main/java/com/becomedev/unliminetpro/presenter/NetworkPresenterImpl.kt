package com.becomedev.unliminetpro.presenter

import com.becomedev.unliminetpro.model.NetworkData

class NetworkPresenterImpl(val fragmentView: NetworkContract.FragmentView) : NetworkContract.Presenter {

    override fun onLoadJsonDataFinish(networkList: ArrayList<NetworkData.SubNetworkData>) {
        fragmentView.setDataToRecyclerView(networkList)
    }


}