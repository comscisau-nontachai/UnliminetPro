package com.becomedev.unliminetpro.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

object OnclickApplyPromotion {
    fun applyPromotion(context:Context,str:String){
        Dexter.withContext(context)
              .withPermission(Manifest.permission.CALL_PHONE)
              .withListener(object : PermissionListener {
                  override fun onPermissionGranted(response: PermissionGrantedResponse) {

                    val callIntent = Intent(Intent.ACTION_DIAL)
                    callIntent.data = Uri.parse("tel:${Uri.encode(str)}")

                      try {
                          context.startActivity(callIntent)
                      } catch (e: Exception) {
                          Toast.makeText(context,"can't dial with this device",Toast.LENGTH_SHORT).show()
                      }
                  }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    Toast.makeText(context,"permission denied",Toast.LENGTH_SHORT).show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                }
            }).check()
    }
}