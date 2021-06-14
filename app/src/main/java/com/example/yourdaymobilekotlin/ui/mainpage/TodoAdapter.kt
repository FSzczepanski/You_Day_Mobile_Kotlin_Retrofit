package com.example.yourdaymobilekotlin.ui.mainpage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yourdaymobilekotlin.R
import com.example.yourdaymobilekotlin.data.entities.Todo
import com.example.yourdaymobilekotlin.utilities.OnActionDone


class TodoAdapter(val context: Context, viewModel: MainPageViewModel, todosList:java.util.ArrayList<Todo>, onActionDone: OnActionDone) :
RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private var todosList: java.util.ArrayList<Todo> = todosList
    private  var viewModel: MainPageViewModel = viewModel
    private var onActionDone: OnActionDone = onActionDone


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val deleteButton: ImageButton

        init {
            textView = view.findViewById(R.id.todotv)
            deleteButton = view.findViewById(R.id.deleteButton)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.todo_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.textView.text = todosList.get(position).text

        viewHolder.deleteButton.setOnClickListener(View.OnClickListener {
            Log.e("click",todosList.get(position)._id)
            viewModel.deleteTodo(todosList.get(position)._id, object: OnActionDone{
                override fun onDone() {
                    onActionDone.onDone()
                }

            })
        })
    }

    override fun getItemCount() = todosList.size

}



