package com.example.homefinder.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.homefinder.R
import com.example.homefinder.databinding.FragmentLoginBinding
import com.example.homefinder.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private lateinit var emailS: String;
    private lateinit var passwordS: String;
    private var auth: FirebaseAuth? = null
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var preferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preferences = requireActivity().getSharedPreferences(
            "com.example.cryptoproject",
            Context.MODE_PRIVATE
        )
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        val savedString: String? = preferences.getString("stringValue", "Kayıt Yok")
        val savedPassword: String? = preferences.getString("intValue", "bos")
        val savedChecked: Boolean = preferences.getBoolean("isChecked", false)

        if(savedChecked){
            binding.emailt.text = Editable.Factory.getInstance().newEditable(savedString)
            binding.sifret.text = Editable.Factory.getInstance().newEditable(savedPassword.toString())
            binding.checkBox.isChecked = savedChecked
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener {
            emailS = binding.emailt.text.toString()
            var stringValue = binding.emailt.text
            var intValue = binding.sifret.text.toString()
            passwordS = binding.sifret.text.toString()
            var isChecked = binding.checkBox.isChecked()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(emailS, passwordS)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        print("selam")
                        val editor: SharedPreferences.Editor =
                            preferences.edit()
                        editor.putString("stringValue", stringValue.toString())
                        editor.putString("intValue",intValue)
                        editor.putBoolean("isChecked", isChecked)
                        editor.commit()
                        val intent = Intent(requireContext(), ProfileActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(requireContext(), "password or email is incorrect!", Toast.LENGTH_SHORT).show()
                        task.exception!!.message?.let { it1 -> Log.e("Giriş Hatası", it1) }
                    }
                }
        }

    }
}