package com.becomedev.unliminetpro.data.extension

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
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

inline fun <reified T : Enum<T>> Intent.putEnumExtra(victim: T): Intent =
    putExtra(T::class.java.name, victim.ordinal)

inline fun <reified T : Enum<T>> Bundle.getEnumExtra(): T? =
    getInt(T::class.java.name, -1)
        .takeUnless { it == -1 }
        ?.let { T::class.java.enumConstants?.get(it) }


fun Context.callPhone(tel: String) {
    Dexter.withContext(this)
        .withPermission(Manifest.permission.CALL_PHONE)
        .withListener(object : PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse) {

                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:${Uri.encode(tel)}")

                try {
                    this@callPhone.startActivity(callIntent)
                } catch (e: Exception) {
                    Toast.makeText(this@callPhone, "can't dial with this device", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onPermissionDenied(response: PermissionDeniedResponse) {
                Toast.makeText(this@callPhone, "permission denied", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionRationaleShouldBeShown(
                permission: PermissionRequest?,
                token: PermissionToken?
            ) {
            }
        }).check()
}




