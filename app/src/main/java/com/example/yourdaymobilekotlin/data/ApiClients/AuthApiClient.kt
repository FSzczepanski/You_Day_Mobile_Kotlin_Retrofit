package com.example.yourdaymobilekotlin.data.ApiClients

import com.example.yourdaymobilekotlin.data.entities.LoginRequest
import com.example.yourdaymobilekotlin.data.entities.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AuthApiClient {
    @POST("auth/login") fun login(@Body obj: LoginRequest): Call<LoginResponse>


    companion object {

        fun create(): AuthApiClient {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://192.168.0.12:3000/")
                    .build()

            return retrofit.create(AuthApiClient::class.java)
        }
    }
}