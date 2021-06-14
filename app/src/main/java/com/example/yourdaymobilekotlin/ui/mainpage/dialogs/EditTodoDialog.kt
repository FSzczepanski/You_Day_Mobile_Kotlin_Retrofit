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

class EditTodoDialog (itemId: String, itemText: String, mViewModel: MainPageViewModel, onActionDone: OnActionDone) : DialogFragment() {

    private var  viewModel: MainPageViewModel = mViewModel
    private var todoDescription:String = ""
    private var itemId: String = itemId
    private var itemText: String = itemText

    private lateinit var root: View
    private var onActionDone: OnActionDone = onActionDone


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.dialog_edit_todo, container)
        val editTextTodoDesc = root.findViewById<EditText>(R.id.etTodo)
        val textViewEdit = root.findViewById<TextView>(R.id.tvEdit)
        val textViewCancel = root.findViewById<TextView>(R.id.tvCancel)
        val textViewDelete = root.findViewById<TextView>(R.id.tvDelete)

        editTextTodoDesc.setText(itemText.toString())

        //Edycja zadania
        textViewEdit.setOnClickListener {
            todoDescription = editTextTodoDesc.text.toString()
            if (todoDescription!="") {
                viewModel.editTodo(itemId,todoDescription, object: OnActionDone {
                    override fun onDone() {
                        onActionDone.onDone()
                    }

                })
                this@EditTodoDialog.getDialog()?.cancel()
            } else {
                Toast.makeText(requireContext().getApplicationContext(), "Wprowadź prawidłowe dane ", Toast.LENGTH_SHORT).show()
            }
        }

        //Usuwanie zadania
        textViewDelete.setOnClickListener(View.OnClickListener {

            viewModel.deleteTodo(itemId, object: OnActionDone{
                override fun onDone() {
                    onActionDone.onDone()
                }

            })
            this@EditTodoDialog.getDialog()?.cancel()
        })


        //Wyjście z dialogu
        textViewCancel.setOnClickListener { this@EditTodoDialog.getDialog()?.cancel() }

        return root
    }


}