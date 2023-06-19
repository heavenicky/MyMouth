package com.ac.id.umn.mymouth

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.leaderboardlist.view.*

class leaderboardAdapter(private var listLead:List<userLeaderboard> , private var activeAcc:String): RecyclerView.Adapter<leaderboardAdapter.ViewHolder>() {

    class ViewHolder(item:View):RecyclerView.ViewHolder(item){
        val imgProfile : ImageView = item.ptcprofile
        val username : TextView = item.txtusernamelead
        val point : TextView = item.txtpointlead
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): leaderboardAdapter.ViewHolder {
        val resultListLead = LayoutInflater.from(parent.context).inflate(R.layout.leaderboardlist , parent , false)
        return leaderboardAdapter.ViewHolder(resultListLead)
    }

    override fun onBindViewHolder(holder: leaderboardAdapter.ViewHolder, position: Int) {
        holder.username.text = listLead[position].username
        holder.point.text = listLead[position].score.toString()

        if(listLead[position].activePhoto == 1){
            holder.imgProfile.setImageResource(R.drawable.awal)
        }
        if(listLead[position].activePhoto == 2){
            holder.imgProfile.setImageResource(R.drawable.babysick)
        }
        if(listLead[position].activePhoto == 3){
            holder.imgProfile.setImageResource(R.drawable.dentist)
        }
        if(listLead[position].activePhoto == 4){
            holder.imgProfile.setImageResource(R.drawable.malenurse)
        }
        if(listLead[position].activePhoto == 5){
            holder.imgProfile.setImageResource(R.drawable.nurse)
        }
        if(listLead[position].activePhoto == 6){
            holder.imgProfile.setImageResource(R.drawable.mouth)
        }
        if(listLead[position].activePhoto == 7){
            holder.imgProfile.setImageResource(R.drawable.toothbrush)
        }
        if(listLead[position].activePhoto == 8){
            holder.imgProfile.setImageResource(R.drawable.tooth)
        }
        if(listLead[position].activePhoto == 9){
            holder.imgProfile.setImageResource(R.drawable.operationtable)
        }
    }

    override fun getItemCount(): Int {
        return listLead.size
    }


}