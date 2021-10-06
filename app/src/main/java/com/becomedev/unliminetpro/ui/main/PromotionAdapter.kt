package com.becomedev.unliminetpro.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.extension.to2Point
import com.becomedev.unliminetpro.extension.toNumericFormat
import com.becomedev.unliminetpro.model.NetworkPromotionModel
import kotlinx.android.synthetic.main.item_row_network.view.*


class PromotionAdapter : RecyclerView.Adapter<PromotionAdapter.ViewHolder>() {

    var items = mutableListOf<NetworkPromotionModel>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    private var callback : ((NetworkPromotionModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_network,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(network: NetworkPromotionModel) {

            itemView.apply {

                //show package extension
                network.freeCall.let {
                    if(it.isNotEmpty()){
                        layout_free_call.apply {
                            visibility = View.VISIBLE
                            txt_tell_free.text = network.freeCall
                        }
                    }else{
                        layout_free_call.visibility = View.GONE
                    }
                }
                network.freeWifi.let {
                    if(it.isNotEmpty()) {
                        layout_free_wifi.apply {
                            visibility = View.VISIBLE
                            txt_free_wifi.text = network.freeWifi
                        }
                    }else{
                        layout_free_wifi.visibility = View.GONE
                    }
                }

                //set bg color
                when(network.name){
                    "true" -> {
                        cardview_layout.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorTrueHead))
                        layout_icon.setBackgroundColor(ContextCompat.getColor(context,R.color.colorTrueIcon))
                        layout_icon2.setBackgroundColor(ContextCompat.getColor(context,R.color.colorTrueIcon))
                        layout_icon3.setBackgroundColor(ContextCompat.getColor(context,R.color.colorTrueIcon))
                        layout_icon4.setBackgroundColor(ContextCompat.getColor(context,R.color.colorTrueIcon))
                        linearLayout2.setBackgroundColor(ContextCompat.getColor(context,R.color.colorTrueBody))


                    }
                    "ais" -> {
                        cardview_layout.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorAisHead))
                        layout_icon.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAisIcon))
                        layout_icon2.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAisIcon))
                        layout_icon3.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAisIcon))
                        layout_icon4.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAisIcon))
                        linearLayout2.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAisBody))
                    }
                   else -> {
                       cardview_layout.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorDtacHead))
                       layout_icon.setBackgroundColor(ContextCompat.getColor(context,R.color.colorDtacIcon))
                       layout_icon2.setBackgroundColor(ContextCompat.getColor(context,R.color.colorDtacIcon))
                       layout_icon3.setBackgroundColor(ContextCompat.getColor(context,R.color.colorDtacIcon))
                       layout_icon4.setBackgroundColor(ContextCompat.getColor(context,R.color.colorDtacIcon))
                       linearLayout2.setBackgroundColor(ContextCompat.getColor(context,R.color.colorDtacBody))
                   }
                }

                txt_price.text = "${network.price.toNumericFormat()} บาท /  ${network.day} วัน"
                txt_net.text = "${network.qtyNet} ความเร็ว ${network.speedNet}"
                txt_total.text = "ราคารวมภาษี ${ ((network.price*7f/100f)+network.price).to2Point() } บาท"
                txt_tel.text = network.tel

                btn_apply.setOnClickListener {
                    //recyclerItemClickListener.onItemClick(network.tel)
                    callback?.invoke(network)
                }
            }
        }
    }

    fun setOnItemClick(cb : ((NetworkPromotionModel) -> Unit)?){
        this.callback = cb
    }
}
