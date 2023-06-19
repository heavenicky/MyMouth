package com.ac.id.umn.mymouth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_login1.*


class Login1Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        btnlogin1.setOnClickListener {
            if(edtEmail.text.isEmpty() || edtEmail.text.isBlank()){
                Toast.makeText(requireActivity(),"Email cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else if(edtPassword.text.isEmpty() || edtPassword.text.isBlank()){
                Toast.makeText(requireActivity(),"Password cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else {
                mAuth.signInWithEmailAndPassword(edtEmail.text.toString(), edtPassword.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent (getActivity(), HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        getActivity()?.startActivity(intent)

                    }
                    else{
                        Toast.makeText(requireActivity(),"Email or Password is Invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
    }

}