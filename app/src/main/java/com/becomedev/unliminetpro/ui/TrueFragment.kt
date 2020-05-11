package com.becomedev.unliminetpro.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.extension.init
import com.becomedev.unliminetpro.model.NetworkData
import com.becomedev.unliminetpro.presenter.NetworkContract
import com.becomedev.unliminetpro.presenter.NetworkPresenterImpl
import com.becomedev.unliminetpro.ui.adapter.NetworkAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_true.*

class TrueFragment : Fragment(),NetworkContract.MainView {

    lateinit var presenterImpl : NetworkPresenterImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_true, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenterImpl = NetworkPresenterImpl(this)

        getListNetworkTrue()
    }

    override fun setDataToRecyclerView(networkList: ArrayList<NetworkData.SubNetworkData>) {
        recycler_true.apply {
            init()
            adapter = NetworkAdapter(networkList)
        }
    }

    private fun getListNetworkTrue(){

        val inputStream = activity!!.assets.open("data_true.json")
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, charset("UTF-8"))
        val gson = Gson().fromJson<NetworkData>(json, NetworkData::class.java)

        presenterImpl.onLoadJsonDataFinish(gson.list)

    }

}
