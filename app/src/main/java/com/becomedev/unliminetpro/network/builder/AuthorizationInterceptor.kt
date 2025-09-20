package com.becomedev.unliminetpro.network.builder

import android.content.Context
import com.becomedev.unliminetpro.data.constant.ApplicationConstantKey
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor(private val context: Context, private val appId: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //val token: String? = AppManager(context).getAuthToken()

//        val location: Location? = null
//        val lat: Double = location?.latitude ?: 0.0
//        val lng: Double = location?.longitude ?: 0.0

        val deviceType = ApplicationConstantKey.DEVICE_TYPE

        val language = "th"

        val original: Request = chain.request()
        val request: Request = original.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("User-Agent", "$appId $deviceType")
            .addHeader("Accept", "application/json")
            //.addHeader("Authorization", "Bearer $token")
            .addHeader("Accept-Language", language)
            .addHeader("app_name", "UnliminetPro")
            .method(original.method, original.body)
            .build()
        return chain.proceed(request)
    }
}