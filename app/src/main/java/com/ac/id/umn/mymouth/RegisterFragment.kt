package com.ac.id.umn.mymouth

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
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.edtPasswordregister
import kotlinx.android.synthetic.main.fragment_register.edtPasswordregisterconfirm
import java.util.*


class RegisterFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()
        navController = Navigation.findNavController(view)

        btnregister1.setOnClickListener {
            if(edtuserregister.text.isEmpty() || edtuserregister.text.isBlank()){
                Toast.makeText(requireActivity(),"Username cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else if(edtEmailregister.text.isEmpty() || edtEmailregister.text.isBlank()){
                Toast.makeText(requireActivity(),"Email cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else if(edtPasswordregister.text.isEmpty() || edtPasswordregister.text.isBlank()){
                Toast.makeText(requireActivity(),"Password cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else if(edtPasswordregisterconfirm.text.isEmpty() || edtPasswordregisterconfirm.text.isBlank()){
                Toast.makeText(requireActivity(),"Confirm password cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else if(edtPasswordregister.text.toString() == edtPasswordregisterconfirm.text.toString()){
                var photoList: ArrayList<Int> = ArrayList()
                var levelList: ArrayList<String> = ArrayList()
                var achievement:ArrayList<Int> = ArrayList()
                levelList.add("0")
                photoList.add(1)
                val dataUser = userAccount(edtuserregister.text.toString(), edtEmailregister.text.toString(), edtPasswordregister.text.toString(), 1,photoList ,0, 0, photoList,"",levelList,0
                        ,0,0,0,0,0,0,0,0,0,0)
                mAuth.createUserWithEmailAndPassword(edtEmailregister.text.toString(), edtPasswordregister.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        val id = it.getResult().user?.uid
                        database.collection("ms_user").document(id.toString()).set(dataUser).addOnCompleteListener {
                            if(it.isSuccessful){
                                navController.navigate(R.id.action_registerFragment_to_login1Fragment)
                                /*Toast.makeText(requireActivity(),"Register Success", Toast.LENGTH_SHORT).show();*/
                            }
                            else{
                                Toast.makeText(requireActivity(), it.exception!!.message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
            else{
                Toast.makeText(requireActivity(),"Password must be same!", Toast.LENGTH_SHORT).show();
            }

        }
    }

}