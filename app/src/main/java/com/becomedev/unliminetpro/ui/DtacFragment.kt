package com.becomedev.unliminetpro.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.extension.init
import com.becomedev.unliminetpro.model.NetworkData
import com.becomedev.unliminetpro.presenter.NetworkContract
import com.becomedev.unliminetpro.presenter.NetworkPresenterImpl
import com.becomedev.unliminetpro.ui.adapter.NetworkAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_dtac.*


class DtacFragment : Fragment() , NetworkContract.FragmentView,NetworkContract.ItemClick {
    lateinit var presenterImpl : NetworkPresenterImpl
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dtac, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenterImpl = NetworkPresenterImpl(this)

        getListNetworkDtac()
    }
    private fun getListNetworkDtac(){

        val inputStream = activity!!.assets.open("data_dtac.json")
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, charset("UTF-8"))
        val gson = Gson().fromJson<NetworkData>(json, NetworkData::class.java)

        presenterImpl.onLoadJsonDataFinish(gson.list)

    }

    override fun setDataToRecyclerView(networkList: ArrayList<NetworkData.SubNetworkData>) {
        recycler_dtac.apply {
            init()
            adapter = NetworkAdapter(networkList,this@DtacFragment)
        }
    }

    override fun onItemClick(str: String) {
        OnclickApplyPromotion.applyPromotion(context!!,str)
    }



}
