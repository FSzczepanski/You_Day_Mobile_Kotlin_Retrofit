package com.example.yourdaymobilekotlin.ui.mainpage

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yourdaymobilekotlin.data.repos.RepositoryTodos
import com.example.yourdaymobilekotlin.data.entities.Todo
import com.example.yourdaymobilekotlin.utilities.OnActionDone
import java.util.ArrayList

class MainPageViewModel : ViewModel() {
    private lateinit var context: Context;

    val todosLiveData = MutableLiveData<ArrayList<Todo>>()
    private lateinit var repositoryTodos: RepositoryTodos



     fun setContext(context: Context){
        this.context = context
         repositoryTodos = RepositoryTodos(context)
    }


    fun getTodosList() {
       repositoryTodos.getTodosList(object: TodosCallback {
           override fun onCallback(todos: ArrayList<Todo>) {
                todosLiveData.postValue(todos)
           }

       })
    }

    fun createTodo(todoText: String){
        repositoryTodos.addTodo(todoText, object: OnActionDone {
            override fun onDone() {
                Log.e("added","added todo")
                getTodosList()
            }

        })
    }

    fun deleteTodo(id: String) {
        repositoryTodos.deleteTodo(id,object: OnActionDone{
            override fun onDone() {
                getTodosList()
            }

        })
    }

    fun editTodo(id: String, todoText: String) {
        repositoryTodos.editTodo(id,todoText,object: OnActionDone{
            override fun onDone() {
                getTodosList()
            }

        })

    }
}