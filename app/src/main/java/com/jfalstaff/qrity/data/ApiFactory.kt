package com.jfalstaff.qrity.data

import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_QR_URL = "https://api.qrserver.com/v1/create-qr-code/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_QR_URL)
        .build()

    val api = retrofit.create(ApiService::class.java)
}