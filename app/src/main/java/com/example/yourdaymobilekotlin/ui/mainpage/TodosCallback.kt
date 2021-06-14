package com.example.yourdaymobilekotlin.ui.mainpage

import com.example.yourdaymobilekotlin.data.entities.Todo
import java.util.ArrayList

interface TodosCallback {
    fun onCallback(todos: ArrayList<Todo>)
}