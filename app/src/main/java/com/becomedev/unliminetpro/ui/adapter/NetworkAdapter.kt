package com.becomedev.unliminetpro.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.model.NetworkData
import kotlinx.android.synthetic.main.item_row.view.*

class NetworkAdapter(private val items : ArrayList<NetworkData.SubNetworkData>) : RecyclerView.Adapter<NetworkAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false))
    }

    override fun getItemCount(): Int = items.size?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(network : NetworkData.SubNetworkData){
            itemView.apply {
                txt_tel.text = network.tel
            }
        }
    }
}