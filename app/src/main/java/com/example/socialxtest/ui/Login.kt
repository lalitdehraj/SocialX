package com.example.socialxtest.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.socialxtest.R
import com.example.socialxtest.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class Login : Fragment(R.layout.fragment_login) {
    private lateinit var binding : FragmentLoginBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    companion object {
        private const val GOOGLE_SIGN_IN = 120
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        mAuth = FirebaseAuth.getInstance()

        val user = mAuth.currentUser
        if (user != null)
            findNavController().navigate(R.id.action_login_to_newsFeed)


        binding.signupTab.setOnClickListener({
            findNavController().navigate(R.id.action_login_to_signUp)
        })
        binding.registerNowLabel.setOnClickListener({
            findNavController().navigate(R.id.action_login_to_signUp)
        })

        binding.googleSigninImage.setOnClickListener({
            googleSignIn()
        })

        binding.facebookSigninImage.setOnClickListener({

        })


    }

    private fun googleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.Client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                }
            }
            Log.d("Login fragment : ", exception?.message.toString())
        }
        else
            Toast.makeText(requireContext(), "Login Eror ", Toast.LENGTH_SHORT)

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("SignInActivity", "signInWithCredential:success")
                    findNavController().navigate(R.id.action_login_to_newsFeed)

                } else {
                    Log.d("SignInActivity", "signInWithCredential:failure")
                }
            }
    }



}