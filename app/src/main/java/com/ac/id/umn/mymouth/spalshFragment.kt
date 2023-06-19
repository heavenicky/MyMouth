package com.ac.id.umn.mymouth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_spalsh.*


class spalshFragment : Fragment() {


    lateinit var mauth : FirebaseAuth
    lateinit var handler: Handler


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spalsh, container, false)
    }

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mauth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)

       /* btnsplashscreen.setOnClickListener {

            if(mauth.currentUser != null){
                val intent = Intent (getActivity(), HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                getActivity()?.startActivity(intent)
            }
            else(navController.navigate(R.id.action_spalshFragment_to_loginFragment))
        }*/

        handler = Handler()
        handler.postDelayed({
            if(mauth.currentUser != null){
               /* val svc = Intent(this, BackgroundMusic::class.java)
                svc.setAction("PLAY")
                startService(svc)*/
                val intent = Intent (requireActivity(), HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }else{
                /*val svc = Intent(this, BackgroundMusic::class.java)
                svc.setAction("PLAY")
                startService(svc)*/
                /*val intent = Intent(requireActivity(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)*/
                //finish()
                navController.navigate(R.id.action_spalshFragment_to_loginFragment)
            }
        },3000)



    }


}