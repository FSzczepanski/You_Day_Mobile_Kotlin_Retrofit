package com.example.yourdaymobilekotlin.ui.auth

import com.example.yourdaymobilekotlin.data.entities.LoginRequest
import com.example.yourdaymobilekotlin.data.entities.TodoRequest
import com.example.yourdaymobilekotlin.ui.mainpage.TodoApiClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AuthApiClient {
    @POST("auth/login") fun login(@Body obj: LoginRequest): Call<Object>


    companion object {

        fun create(): AuthApiClient {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://localhost:3000/")
                    .build()

            return retrofit.create(AuthApiClient::class.java)
        }
    }
}