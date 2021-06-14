package com.example.yourdaymobilekotlin.ui.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.yourdaymobilekotlin.data.repos.RepositoryAuth
import com.example.yourdaymobilekotlin.data.repos.RepositoryTodos
import com.example.yourdaymobilekotlin.utilities.OnActionDone

class AuthViewModel : ViewModel() {

    private lateinit var repositoryAuth: RepositoryAuth
    private lateinit var context: Context;



    fun setContext(context: Context){
        this.context = context
        repositoryAuth = RepositoryAuth(context)
    }

    fun login(email: String, password: String, authCallback: AuthCallback){
        repositoryAuth.login(email,password, object: AuthCallback{
            override fun onCallback(loggedIn: Boolean) {
                authCallback.onCallback(loggedIn)
            }
        })

    }
}