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
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_login1.*
import kotlinx.android.synthetic.main.fragment_login1.btnlogin1
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_shop.*


class HomeFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController
    var totalcoin = 0
    var achiev = ""

    var listAchiev:ArrayList<Int> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()
        navController = Navigation.findNavController(view)

        database.collection("ms_user").document(mAuth.uid.toString())
            .get().addOnSuccessListener {
                totalcoin = it.getLong("money")!!.toInt()
                txtcoinmenu.text = totalcoin.toString()
                    achiev = it.get("achievementList").toString()
                var photoid = it.getLong("activePhoto")!!.toInt()
                    if(photoid == 1){
                        profilepict.setImageResource(R.drawable.awal)
                    }
                    if(photoid == 2){
                        profilepict.setImageResource(R.drawable.babysick)
                    }
                    if(photoid == 3){
                        profilepict.setImageResource(R.drawable.dentist)
                    }
                    if(photoid == 4){
                        profilepict.setImageResource(R.drawable.malenurse)
                    }
                    if(photoid == 5){
                        profilepict.setImageResource(R.drawable.nurse)
                    }
                    if(photoid == 6){
                        profilepict.setImageResource(R.drawable.mouth)
                    }
                    if(photoid == 7){
                        profilepict.setImageResource(R.drawable.toothbrush)
                    }
                    if(photoid == 8){
                        profilepict.setImageResource(R.drawable.tooth)
                    }
                    if(photoid == 9){
                        profilepict.setImageResource(R.drawable.operationtable)
                    }

                    if(achiev.contains("1")){
                        listAchiev.add(1)

                    }
                    if(achiev.contains("2")){
                        listAchiev.add(2)

                    }
                    if(achiev.contains("3")){
                        listAchiev.add(3)

                    }
                    if(achiev.contains("4")){
                        listAchiev.add(4)

                    }
                    coinmenu.setOnClickListener {
                        if(!achiev.contains("4")){
                            listAchiev.add(4)
                            database.collection("ms_user").document(mAuth.uid.toString()).update("achievementList",listAchiev )
                            database.collection("ms_user").document(mAuth.uid.toString())
                                    .get().addOnSuccessListener {
                                        achiev = it.get("achievementList").toString()
                                    }
                            Toast.makeText( requireContext(),"You Have Unlocked New Achievement", Toast.LENGTH_SHORT).show()
                        }
                    }

            }


        btnLeaderboard.setOnClickListener {
            navController.navigate(R.id.action_homeFragment2_to_leaderboardFragment)
            }

        btnLevel.setOnClickListener {
            navController.navigate(R.id.action_homeFragment2_to_levelFragment)
        }

        btnShop.setOnClickListener {
            navController.navigate(R.id.action_homeFragment2_to_shopFragment)
        }

        btnAchievement.setOnClickListener {
            navController.navigate(R.id.action_homeFragment2_to_achievementFragment)
        }

        profilepict.setOnClickListener {
            navController.navigate(R.id.action_homeFragment2_to_profileFragment)
        }

        /*coinmenu.setOnClickListener {
            if(!achiev.contains("4")){
                listAchiev.add(4)
                database.collection("ms_user").document(mAuth.uid.toString()).update("achievementList",listAchiev )
                database.collection("ms_user").document(mAuth.uid.toString())
                        .get().addOnSuccessListener {
                            achiev = it.get("achievementList").toString()
                        }
                Toast.makeText( requireContext(),"You Have Unlocked New Achievement", Toast.LENGTH_SHORT).show()
            }
        }*/

    }

}