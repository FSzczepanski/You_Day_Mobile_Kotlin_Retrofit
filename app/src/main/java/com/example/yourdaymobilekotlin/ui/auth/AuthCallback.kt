package com.example.yourdaymobilekotlin.ui.auth

interface AuthCallback {
    fun onCallback(loggedIn: Boolean)
}