package com.ac.id.umn.mymouth

import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.fragment_achievement.*
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.fragment_shop.listshop


class AchievementFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController

    var userAchievement = ""

    var listData:ArrayList<userAchievement> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_achievement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        database = FirebaseFirestore.getInstance()

        database.collection("ms_user").document(mAuth.uid.toString())
                .get().addOnSuccessListener {

                    listData.add(userAchievement("Create An Account", R.drawable.achieve2, 1))
                    listData.add(userAchievement("FInish Theory 1 with Perfect Score", R.drawable.achieve3, 2))
                    listData.add(userAchievement("Finish All Theories", R.drawable.achieve1, 3))
                    listData.add(userAchievement("????????", R.drawable.coin, 4))

                    userAchievement = it.get("achievementList").toString()

                    listachievement.adapter = achievementAdapter(listData, userAchievement)
                    listachievement.layoutManager = LinearLayoutManager(requireContext())
                }



    }

}