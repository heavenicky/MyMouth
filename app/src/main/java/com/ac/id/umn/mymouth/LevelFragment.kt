package com.ac.id.umn.mymouth

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
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import kotlinx.android.synthetic.main.fragment_level.*

class LevelFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController

    var listData:ArrayList<Int> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()
        navController = Navigation.findNavController(view)
        listData.add(0)
        listData.add(1)
        listData.add(2)
        listData.add(3)
        listData.add(4)
        listData.add(5)
        listData.add(6)
        listData.add(7)
/*        listData.add(8)
        listData.add(9)*/
/*        listData.add(10)
        listData.add(11)
        listData.add(12)
        listData.add(13)
        listData.add(14)
        listData.add(15)
        listData.add(16)*/

        database.collection("ms_user").document(mAuth.uid.toString())
                .get().addOnSuccessListener {
                   var levellist = it.get("level").toString()
                    /*if(!levellist.contains("2")){

                    }*/
                    listlevel.adapter = levelAdapter(listData, requireActivity(),levellist)
                    listlevel.layoutManager = LinearLayoutManager(requireContext())
                }


    }


}