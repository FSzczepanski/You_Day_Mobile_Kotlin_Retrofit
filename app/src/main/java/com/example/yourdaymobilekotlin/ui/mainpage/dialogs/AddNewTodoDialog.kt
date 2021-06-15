package com.example.yourdaymobilekotlin.ui.mainpage.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.yourdaymobilekotlin.R
import com.example.yourdaymobilekotlin.ui.mainpage.MainPageViewModel
import com.example.yourdaymobilekotlin.utilities.OnActionDone

class AddNewTodoDialog(mViewModel: MainPageViewModel) : DialogFragment() {

    private var  viewModel: MainPageViewModel = mViewModel
    private var todoDescription:String = ""

    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         root = inflater.inflate(R.layout.dialog_add_todo, container)
        val editTextTodoDesc = root.findViewById<EditText>(R.id.etTodo)
        val textViewAdd = root.findViewById<TextView>(R.id.tvAdd)
        val textViewCancel = root.findViewById<TextView>(R.id.tvCancel)

        textViewAdd.setOnClickListener {
            todoDescription = editTextTodoDesc.text.toString()
            if (todoDescription!="") {


                viewModel.createTodo(todoDescription)
                this@AddNewTodoDialog.getDialog()?.cancel()
            } else {
                Toast.makeText(requireContext().getApplicationContext(), "Wprowadź prawidłowe dane ", Toast.LENGTH_SHORT).show()
            }
        }
        textViewCancel.setOnClickListener { this@AddNewTodoDialog.getDialog()?.cancel() }

        return root
    }


}
