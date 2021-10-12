package com.example.homefinder.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.homefinder.R
import com.example.homefinder.databinding.FragmentSignupBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupFragment : Fragment() {
    private lateinit var emailS: String;
    private lateinit var passwordS: String;
    private var auth: FirebaseAuth? = null
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.signButton.setOnClickListener {
            emailS = binding.emaill.text.toString()
            passwordS = binding.password.text.toString()
            SignUp(emailS, passwordS)
            (activity as MainActivity).chooseTab(0)

        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun SignUp(mail: String, sifre: String) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, sifre)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(test: Task<AuthResult>) {
                    if (test.isSuccessful) {
                        Toast.makeText(requireContext(),"Successfully registered",Toast.LENGTH_LONG).show()


                    }
                }
            })

    }
}
