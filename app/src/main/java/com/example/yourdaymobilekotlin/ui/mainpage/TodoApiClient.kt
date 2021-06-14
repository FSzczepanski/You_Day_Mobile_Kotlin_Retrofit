package com.example.yourdaymobilekotlin.ui.mainpage

import com.example.yourdaymobilekotlin.data.entities.Todo
import com.example.yourdaymobilekotlin.data.entities.TodoRequest
import io.reactivex.Completable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface TodoApiClient {
    @GET("note") fun getTodos(@Header("auth-token") token: String): Call<java.util.ArrayList<Todo>>
    @POST("note") fun addTodo(@Body obj: TodoRequest, @Header("auth-token") token: String): Call<Object>
    @DELETE("note/{id}") fun deleteTodo(@Path("id") id: String): Call<Object>
    @PATCH("note/{id}") fun updateTodo(@Path("id")id: String, @Body text: Todo): Completable

    companion object {

        fun create(): TodoApiClient {


            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://localhost:3000/")
                    .build()

            return retrofit.create(TodoApiClient::class.java)
        }
    }
}