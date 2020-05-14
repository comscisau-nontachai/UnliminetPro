package com.becomedev.unliminetpro.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat

fun RecyclerView.init(){
    this.apply {
        this.layoutManager = LinearLayoutManager(this.context)
        this.hasFixedSize()
    }
}

fun Float.to2Point():String{
    return NumberFormat.getInstance().format(this)
}
fun Int.toNumericFormat():String{
    return NumberFormat.getInstance().format(this)
}





