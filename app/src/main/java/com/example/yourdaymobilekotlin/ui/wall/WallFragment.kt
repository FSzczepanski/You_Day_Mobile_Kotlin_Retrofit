package com.example.yourdaymobilekotlin.ui.wall

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yourdaymobilekotlin.R

class WallFragment : Fragment() {

    companion object {
        fun newInstance() = WallFragment()
    }

    private lateinit var viewModel: WallViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wall_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WallViewModel::class.java)
        // TODO: Use the ViewModel
    }

}