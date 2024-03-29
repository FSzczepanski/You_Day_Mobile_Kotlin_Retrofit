package com.example.yourdaymobilekotlin.ui.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourdaymobilekotlin.MainActivity
import com.example.yourdaymobilekotlin.R
import com.example.yourdaymobilekotlin.data.entities.Todo
import com.example.yourdaymobilekotlin.ui.mainpage.dialogs.AddNewTodoDialog
import com.example.yourdaymobilekotlin.utilities.OnActionDone
import com.example.yourdaymobilekotlin.utilities.ProgressBarInit
import com.example.yourdaymobilekotlin.utilities.TabLayoutDisabler
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainPageFragment : Fragment(),TabLayoutDisabler, ProgressBarInit {

    companion object {
        fun newInstance() = MainPageFragment()
    }

    private lateinit var viewModel: MainPageViewModel
    private lateinit var root: View
    private lateinit var adapter: TodoAdapter
    private lateinit var todosRv:RecyclerView
    private lateinit var todosList: ArrayList<Todo>
    private lateinit var dialog: AddNewTodoDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showTabLayout();

        root= inflater.inflate(R.layout.main_page_fragment, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainPageViewModel::class.java)
        context?.let { viewModel.setContext(it) }
        setUpList()
        setupAddTodoDialog()
    }

    private fun setUpList(){
        showProgressBar()
        viewModel.getTodosList()
        todosRv= root.findViewById(R.id.todosRV)
        todosRv.layoutManager = LinearLayoutManager(context)

        viewModel.todosLiveData.observe(viewLifecycleOwner, Observer {t->
            run {
                adapter = context?.let {
                    TodoAdapter(requireContext(), viewModel, parentFragmentManager, t)
                }!!
                todosRv.adapter = adapter
                hideProgressBar()

            }
        })



    }



    private fun setupAddTodoDialog(){
        val addButton: FloatingActionButton = root.findViewById(R.id.addNewTodo)
        addButton.setOnClickListener {
            dialog = AddNewTodoDialog(viewModel)
            dialog.show(parentFragmentManager, "DialogFragment")
        }
    }




    override fun hideTabLayout() {
        TODO("Not yet implemented")
    }

    override fun showTabLayout() {
        (activity as MainActivity).showTabLayout();
    }

    override fun hideProgressBar() {
        (activity as MainActivity).hideProgressBar();
    }

    override fun showProgressBar() {
        (activity as MainActivity).showProgressBar();
    }

}