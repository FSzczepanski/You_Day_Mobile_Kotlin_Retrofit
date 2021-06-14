package com.example.yourdaymobilekotlin.ui.mainpage

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.yourdaymobilekotlin.data.repos.RepositoryTodos
import com.example.yourdaymobilekotlin.data.entities.Todo
import com.example.yourdaymobilekotlin.utilities.OnActionDone
import java.util.ArrayList

class MainPageViewModel : ViewModel() {
    private lateinit var context: Context;

    private lateinit var todos: java.util.ArrayList<Todo>
    private lateinit var repositoryTodos: RepositoryTodos



     fun setContext(context: Context){
        this.context = context
         repositoryTodos = RepositoryTodos(context)
    }


    fun getTodosList(todosCallback: TodosCallback) {
       repositoryTodos.getTodosList(object: TodosCallback {
           override fun onCallback(todos: ArrayList<Todo>) {
               todosCallback.onCallback(todos)
           }

       })
    }

    fun createTodo(todoText: String, onActionDone: OnActionDone){
        repositoryTodos.addTodo(todoText, object: OnActionDone {
            override fun onDone() {
                Log.e("added","added todo")
                onActionDone.onDone()
            }

        })
    }

    fun deleteTodo(id: String, onActionDone: OnActionDone) {
        repositoryTodos.deleteTodo(id,object: OnActionDone{
            override fun onDone() {
                onActionDone.onDone()
            }

        })
    }
}