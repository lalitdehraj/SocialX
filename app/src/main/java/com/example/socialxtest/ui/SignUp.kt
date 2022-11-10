package com.example.socialxtest.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.socialxtest.R
import com.example.socialxtest.databinding.FragmentSignUpBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SignUp : Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding : FragmentSignUpBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_login)
        }
        binding.registerButton.setOnClickListener {
            lifecycleScope.launch {
                binding.progressBar.isVisible= true
                delay(2000)
                findNavController().navigate(R.id.action_signUp_to_newsFeed)
            }
        }
    }

}