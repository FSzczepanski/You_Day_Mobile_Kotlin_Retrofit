package com.example.yourdaymobilekotlin.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.yourdaymobilekotlin.MainActivity
import com.example.yourdaymobilekotlin.R
import com.example.yourdaymobilekotlin.utilities.OnActionDone
import com.example.yourdaymobilekotlin.utilities.ProgressBarInit
import com.example.yourdaymobilekotlin.utilities.SessionManager
import com.example.yourdaymobilekotlin.utilities.TabLayoutDisabler
import org.json.JSONException
import java.io.IOException

class AuthFragment : Fragment(),TabLayoutDisabler, ProgressBarInit{

    companion object {
        fun newInstance() = AuthFragment()
    }

    private lateinit var viewModel: AuthViewModel
    private lateinit var root: View
    private lateinit var sessionManager: SessionManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.auth_fragment, container, false)

        hideTabLayout()
        hideProgressBar()
        return root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        context?.let { viewModel.setContext(it) }
        checkIfLoggedIn()
        setupLogin();
    }

    fun checkIfLoggedIn(){
        sessionManager = context?.let { SessionManager(it) }!!;
        if(sessionManager.fetchAuthToken().toString()!=""){

            //TODO CHECK AUTHTOKEN
            findNavController().navigate(R.id.action_authFragment_to_mainPageFragment)
        }
    }

    fun setupLogin(){
        var login:AppCompatButton =  root.findViewById(R.id.loginButton);

        login.setOnClickListener(View.OnClickListener {
            showProgressBar()
            val emailEdit = root.findViewById<EditText>(R.id.loginEmail)
            val passwordEdit = root.findViewById<EditText>(R.id.loginPassword)
            val email = emailEdit.text.toString()
            val password = passwordEdit.text.toString()

            try {
                signIn(email, password)
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }


        })
    }

    fun signIn(email: String, password: String){
        showProgressBar()
        viewModel.login(email,password, object: AuthCallback{
            override fun onCallback(loggedIn: Boolean) {
                if(loggedIn){
                    findNavController().navigate(R.id.action_authFragment_to_mainPageFragment)
                }else{
                    Toast.makeText(context, "Nieprawidłowe dane", Toast.LENGTH_LONG).show()
                }
                hideProgressBar()

            }

        })




    }


    override fun hideTabLayout() {
        (activity as MainActivity).hideTabLayout();
    }

    override fun showTabLayout() {}

    override fun hideProgressBar() {
        (activity as MainActivity).hideProgressBar();
    }

    override fun showProgressBar() {
        (activity as MainActivity).showProgressBar();
    }




}