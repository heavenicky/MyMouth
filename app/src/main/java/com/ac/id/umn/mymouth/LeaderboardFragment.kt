package com.ac.id.umn.mymouth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import kotlinx.android.synthetic.main.fragment_login1.*


class LeaderboardFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController

    var userData = ""

    var listData:ArrayList<userLeaderboard> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        database = FirebaseFirestore.getInstance()

        database.collection("ms_user").document(mAuth.uid.toString())
                .get().addOnSuccessListener {
                    userData = it.getString("username").toString()
                    database.collection("ms_user").orderBy("score", Query.Direction.DESCENDING).get().addOnSuccessListener {
                        for (i in it){
                            listData.add(userLeaderboard(i.getString("username").toString(), i.getLong("score")!!.toInt(),
                                    i.getLong("activePhoto")!!.toInt() ))
                        }

                        listleaderboard.adapter = leaderboardAdapter(listData, userData)
                        listleaderboard.layoutManager = LinearLayoutManager(requireContext())
                    }
                }

    }


}