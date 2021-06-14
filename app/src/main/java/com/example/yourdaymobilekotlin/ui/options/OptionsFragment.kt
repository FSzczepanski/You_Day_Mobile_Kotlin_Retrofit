package com.example.yourdaymobilekotlin.ui.options

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.yourdaymobilekotlin.R
import com.example.yourdaymobilekotlin.utilities.SessionManager

class OptionsFragment : Fragment() {

    companion object {
        fun newInstance() = OptionsFragment()
    }

    private lateinit var viewModel: OptionsViewModel
    private lateinit var sessionManager: SessionManager
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.options_fragment, container, false)
        return root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OptionsViewModel::class.java)
        logout()

    }

    private fun logout(){
        var logoutButton: View =  root.findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener(View.OnClickListener {
            sessionManager = context?.let { SessionManager(it) }!!;
            sessionManager.saveAuthToken("")
            findNavController().navigate(R.id.action_optionsFragment_to_authFragment)
        })

    }
}