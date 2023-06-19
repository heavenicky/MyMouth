package com.ac.id.umn.mymouth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_endgame.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.txtcoin
import kotlinx.android.synthetic.main.fragment_profile.txtrank

class EndgameFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController

    var achiev = ""

    var listAchiev:ArrayList<Int> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_endgame, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        database = FirebaseFirestore.getInstance()

        database.collection("ms_user").document(mAuth.uid.toString())
                .get().addOnSuccessListener {
                    var updatescore = it.getLong("updatescore")!!.toInt()
                    var updatecoin = it.getLong("updatescore")!!.toInt()

                    nilaiscore.text = updatescore.toString()
                    nilaicoin.text = updatecoin.toString()

                }

        btnFinish.setOnClickListener {
            val intent = Intent (requireActivity(), HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            requireActivity()?.startActivity(intent)
        }
    }

}