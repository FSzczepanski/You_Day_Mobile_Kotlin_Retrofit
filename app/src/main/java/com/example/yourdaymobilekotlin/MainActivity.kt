package com.example.yourdaymobilekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView;
    private lateinit var toolbar: View;
    private lateinit var navController: NavController;
    private lateinit var progressBar: View;

    private var authToken: String = "";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this,R.id.my_nav_host_fragment);

        initBottomTabNav();

        toolbar = findViewById(R.id.toolbar);

    }

    private fun initBottomTabNav(){
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    fun showProgressBar(){
        progressBar = findViewById(R.id.progressBar);
        progressBar.visibility = View.VISIBLE;
    }

    fun hideProgressBar(){
        progressBar = findViewById(R.id.progressBar);
        progressBar.visibility = View.GONE;
    }


    fun showTabLayout(){
        if(bottomNavigationView.isInvisible){
            bottomNavigationView.visibility = View.VISIBLE;
            toolbar.visibility = View.VISIBLE;
        }


    }
    fun hideTabLayout(){
        bottomNavigationView.visibility = View.INVISIBLE;
        toolbar.visibility = View.INVISIBLE;
    }
}