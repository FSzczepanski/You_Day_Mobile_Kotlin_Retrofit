package com.example.yourdaymobilekotlin.ui.mainpage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourdaymobilekotlin.R
import com.example.yourdaymobilekotlin.data.entities.Todo
import com.example.yourdaymobilekotlin.ui.mainpage.dialogs.EditTodoDialog
import com.example.yourdaymobilekotlin.utilities.OnActionDone


class TodoAdapter(val context: Context, viewModel: MainPageViewModel,fm: FragmentManager, todosList:java.util.ArrayList<Todo>) :
RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private var todosList: java.util.ArrayList<Todo> = todosList
    private  var viewModel: MainPageViewModel = viewModel
    private var fm: FragmentManager = fm
    private lateinit var dialog: EditTodoDialog



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val editButton : ImageButton

        init {
            textView = view.findViewById(R.id.todotv)
            editButton = view.findViewById(R.id.editButton)
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
        viewHolder.editButton.setOnClickListener(View.OnClickListener {
            dialog = EditTodoDialog(todosList.get(position)._id, todosList.get(position).text, viewModel)
            dialog.show(fm, "DialogFragment")
        })

    }


    override fun getItemCount() = todosList.size

}



