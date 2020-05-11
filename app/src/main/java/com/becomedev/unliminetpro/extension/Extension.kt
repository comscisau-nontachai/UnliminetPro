package com.becomedev.unliminetpro.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.init(){
    this.apply {
        this.layoutManager = LinearLayoutManager(this.context)
        this.hasFixedSize()
    }
}

        //fun MutableList<Int>.swap