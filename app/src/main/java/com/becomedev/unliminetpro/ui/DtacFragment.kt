package com.becomedev.unliminetpro.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.model.NetworkData
import com.becomedev.unliminetpro.ui.adapter.NetworkAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_dtac.*


class DtacFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dtac, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }






}
