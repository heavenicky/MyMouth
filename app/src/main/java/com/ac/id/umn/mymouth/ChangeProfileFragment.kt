package com.ac.id.umn.mymouth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_change_profile.*
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.fragment_shop.listshop


class ChangeProfileFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController
    var listData:ArrayList<avatarList> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        database = FirebaseFirestore.getInstance()

        database.collection("ms_user").document(mAuth.uid.toString())
            .get().addOnSuccessListener {
                val getList = it.get("photoInvent")
                    if(getList.toString().contains("1")){
                        listData.add(avatarList("Default", R.drawable.awal, 1))
                    }

                    if(getList.toString().contains("2")){
                        listData.add(avatarList("Baby Sick", R.drawable.babysick, 2))
                    }
                    if(getList.toString().contains("3")){
                        listData.add(avatarList("Dentist",  R.drawable.dentist, 3))
                    }
                    if(getList.toString().contains("4")){
                        listData.add(avatarList("Male Nurse",  R.drawable.malenurse, 4))
                    }
                    if(getList.toString().contains("5")){
                        listData.add(avatarList("Nurse",  R.drawable.nurse, 5))
                    }
                    if(getList.toString().contains("6")){
                        listData.add(avatarList("Mouth",  R.drawable.mouth, 6))
                    }
                    if(getList.toString().contains("7")){
                        listData.add(avatarList("Toothbrush",  R.drawable.toothbrush, 7))
                    }
                    if(getList.toString().contains("8")){
                        listData.add(avatarList("Tooth",  R.drawable.tooth, 8))
                    }
                    if(getList.toString().contains("9")){
                        listData.add(avatarList("Table",  R.drawable.operationtable, 9))
                    }
/*                Log.e("test",listData.toString())*/
                listavatar.adapter = avatarAdapter(listData, requireActivity(), database, navController)
                listavatar.layoutManager = LinearLayoutManager(requireContext())
            }

        requireActivity()
                .onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navController.navigate(R.id.action_changeProfileFragment_to_profileFragment)
                    }
                })
    }

}