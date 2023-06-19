package com.ac.id.umn.mymouth

import android.content.Intent
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_achievement.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController

    var listData:ArrayList<Int> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        database = FirebaseFirestore.getInstance()

        database.collection("ms_user").document(mAuth.uid.toString())
            .get().addOnSuccessListener {

                var totalcoin = it.getLong("money")!!.toInt()
                var totalscore = it.getLong("score")!!.toInt()
                var username = it.getString("username").toString()
                var photoid = it.getLong("activePhoto")!!.toInt()
                    var badges = it.get("achievementList").toString()

                    if(badges.contains("1")){
                        listData.add(1)
                    }
                    if(badges.contains("2")){
                        listData.add(2)
                    }
                    if(badges.contains("3")){
                        listData.add(3)
                    }
                    if(badges.contains("4")){
                        listData.add(4)
                    }
                    recyclerBadge.adapter = badgesAdapter(listData)


                txtcoin.text = totalcoin.toString()
                txtrank.text = totalscore.toString()
                usernameprofile.text = username

                    if(photoid == 1){
                        profileinpict.setImageResource(R.drawable.awal)
                    }
                    if(photoid == 2){
                        profileinpict.setImageResource(R.drawable.babysick)
                    }
                    if(photoid == 3){
                        profileinpict.setImageResource(R.drawable.dentist)
                    }
                    if(photoid == 4){
                        profileinpict.setImageResource(R.drawable.malenurse)
                    }
                    if(photoid == 5){
                        profileinpict.setImageResource(R.drawable.nurse)
                    }
                    if(photoid == 6){
                        profileinpict.setImageResource(R.drawable.mouth)
                    }
                    if(photoid == 7){
                        profileinpict.setImageResource(R.drawable.toothbrush)
                    }
                    if(photoid == 8){
                        profileinpict.setImageResource(R.drawable.tooth)
                    }
                    if(photoid == 9){
                        profileinpict.setImageResource(R.drawable.operationtable)
                    }
            }

        profileinpict.setOnClickListener {
            navController.navigate(R.id.action_profileFragment_to_changeProfileFragment)
        }

        btnLogout.setOnClickListener{
            mAuth.signOut()
            val intent = Intent (getActivity(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            getActivity()?.startActivity(intent)
        }

        btnback.setOnClickListener {
            navController.navigate(R.id.action_profileFragment_to_homeFragment2)
        }

        requireActivity()
                .onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navController.navigate(R.id.action_profileFragment_to_homeFragment2)
                    }
                })
    }


}