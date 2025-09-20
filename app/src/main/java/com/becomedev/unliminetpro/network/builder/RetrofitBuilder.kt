package com.becomedev.unliminetpro.network.builder

import android.content.Context
import com.becomedev.unliminetpro.BuildConfig
import com.becomedev.unliminetpro.network.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder(private val context: Context) {

    companion object {
        private const val TIME_OUT: Long = 60L
        private const val  BASE_URL = "https://raw.githubusercontent.com/"
        val MEDIA_TYPE_FILE = "multipart/form-result".toMediaTypeOrNull()
    }

    fun api(): ApiService {

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        okHttpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(
            AuthorizationInterceptor(
                context,
                BuildConfig.APPLICATION_ID
            )
        )

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(ApiService::class.java)
    }

}