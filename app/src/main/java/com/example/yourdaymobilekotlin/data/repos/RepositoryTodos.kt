package com.example.yourdaymobilekotlin.data.repos

import android.content.Context
import android.util.Log
import com.example.yourdaymobilekotlin.data.entities.Todo
import com.example.yourdaymobilekotlin.data.entities.TodoRequest
import com.example.yourdaymobilekotlin.utilities.OnActionDone
import com.example.yourdaymobilekotlin.ui.mainpage.TodoApiClient
import com.example.yourdaymobilekotlin.ui.mainpage.TodosCallback
import com.example.yourdaymobilekotlin.utilities.SessionManager
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

class RepositoryTodos( context: Context) {
    init{

    }

    private  var context: Context =context;
    private var sessionManager: SessionManager = context.let { SessionManager(it) }
    private lateinit var todos: java.util.ArrayList<Todo>

    val client by lazy {
        TodoApiClient.create()
    }

    fun getTodosList(todosCallback: TodosCallback){

        client.getTodos(token = "${sessionManager.fetchAuthToken()}")
                .enqueue(object : java.util.ArrayList<Todo>(), retrofit2.Callback<java.util.ArrayList<Todo>> {
                    override fun onFailure(call: Call<ArrayList<Todo>>, t: Throwable) {
                        Log.e("onFailureTodosList",call.toString() + " / "+t.toString())
                    }

                    override fun onResponse(call: Call<ArrayList<Todo>>, response: Response<ArrayList<Todo>>) {
                        Log.e("SuccesfullyFetchedTodosList",response.toString()+ " / "+response.body().toString())
                        todos = response.body()!!
                        todosCallback.onCallback(todos)
                    }

                })
    }


    fun addTodo(todoText: String, onActionDone: OnActionDone){
        var obj: TodoRequest = TodoRequest(todoText)

        client.addTodo(obj = obj,token = "${sessionManager.fetchAuthToken()}")
                .enqueue(object : ArrayList<Object>(), retrofit2.Callback<Object>{
                    override fun onFailure(call: Call<Object>, t: Throwable) {
                        Log.e("onFailureAddingTodo",t.toString())
                    }

                    override fun onResponse(call: Call<Object>, response: Response<Object>) {
                        Log.e("SuccesfullyAddedTodo",response.toString()+ " / "+response.body().toString())
                        onActionDone.onDone()
                    }

                })
    }


    fun deleteTodo(id: String, onActionDone: OnActionDone){
        client.deleteTodo(id = id,token = "${sessionManager.fetchAuthToken()}").enqueue(object : ArrayList<Object>(), retrofit2.Callback<Object>{
            override fun onFailure(call: Call<Object>, t: Throwable) {
                Log.e("onFailureDeletingTodo",t.toString()+"  " +call.toString())
            }

            override fun onResponse(call: Call<Object>, response: Response<Object>) {
                Log.e("SuccesfullyDeletedTodo",response.toString()+ " / "+response.body().toString())
                onActionDone.onDone()
            }


        })
    }


    fun editTodo(id:String ,todoText: String, onActionDone: OnActionDone){
        var obj: TodoRequest = TodoRequest(todoText)

        client.updateTodo(id = id,obj = obj,token = "${sessionManager.fetchAuthToken()}")
                .enqueue(object : ArrayList<Object>(), retrofit2.Callback<Object>{
                    override fun onFailure(call: Call<Object>, t: Throwable) {
                        Log.e("onFailureEditingTodo",t.toString())
                    }

                    override fun onResponse(call: Call<Object>, response: Response<Object>) {
                        Log.e("SuccesfullyEditedTodo",response.toString()+ " / "+response.body().toString())
                        onActionDone.onDone()
                    }

                })
    }


}