package com.example.yourdaymobilekotlin.data.repos

import android.content.Context
import android.util.Log
import com.example.yourdaymobilekotlin.data.entities.LoginRequest
import com.example.yourdaymobilekotlin.data.entities.Todo
import com.example.yourdaymobilekotlin.ui.auth.AuthApiClient
import com.example.yourdaymobilekotlin.ui.mainpage.TodoApiClient
import com.example.yourdaymobilekotlin.utilities.OnActionDone
import com.example.yourdaymobilekotlin.utilities.SessionManager
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

class RepositoryAuth(context: Context) {

    private var context: Context = context;
    private var sessionManager: SessionManager = context.let { SessionManager(it) }

    val client by lazy {
        AuthApiClient.create()
    }

    fun login(email: String, password: String,onActionDone:OnActionDone) {
        sessionManager = context?.let { SessionManager(it) }!!;

        var obj: LoginRequest = LoginRequest(email, password)

        client.login(obj = obj).enqueue(object : ArrayList<Todo>(), retrofit2.Callback<Object> {
            override fun onFailure(call: Call<Object>, t: Throwable) {
                Log.e("failure", t.toString())
            }

            override fun onResponse(call: Call<Object>, response: Response<Object>) {
                Log.e("good", response.toString() + "    " + response.body().toString())
                sessionManager.saveAuthToken("")
                sessionManager.saveAuthToken(response.body().token)
               // sessionManager.saveAuthToken(authToken)
                onActionDone.onDone()
            }

        })
    }

}