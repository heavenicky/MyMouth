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
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import kotlinx.android.synthetic.main.fragment_shop.*


class ShopFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController

    var totalcoin = 0

    var listData:ArrayList<shopList> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        database = FirebaseFirestore.getInstance()

        database.collection("ms_user").document(mAuth.uid.toString())
                .get().addOnSuccessListener {
                    totalcoin = it.getLong("money")!!.toInt()
                    money.text = totalcoin.toString()
                    //Log.e("test",totalcoin.toString())
                    val getList = it.get("photoInvent")
                    if(!getList.toString().contains("2")){
                        listData.add(shopList("Baby Sick", 100, R.drawable.babysick, 2))
                    }
                    if(!getList.toString().contains("3")){
                        listData.add(shopList("Dentist", 1000, R.drawable.dentist, 3))
                    }
                    if(!getList.toString().contains("4")){
                        listData.add(shopList("Male Nurse", 750, R.drawable.malenurse, 4))
                    }
                    if(!getList.toString().contains("5")){
                        listData.add(shopList("Nurse", 750, R.drawable.nurse, 5))
                    }
                    if(!getList.toString().contains("6")){
                        listData.add(shopList("Mouth", 200, R.drawable.mouth, 6))
                    }
                    if(!getList.toString().contains("7")){
                        listData.add(shopList("Toothbrush", 150, R.drawable.toothbrush, 7))
                    }
                    if(!getList.toString().contains("8")){
                        listData.add(shopList("Tooth", 150, R.drawable.tooth, 8))
                    }
                    if(!getList.toString().contains("9")){
                        listData.add(shopList("Table", 500, R.drawable.operationtable, 9))
                    }
                    Log.e("test",listData.toString())
                    listshop.adapter = shopAdapter(listData, requireActivity(), database, money)
                    listshop.layoutManager = LinearLayoutManager(requireContext())
                }

    }

}