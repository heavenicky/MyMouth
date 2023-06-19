package com.ac.id.umn.mymouth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.achievementlist.view.*
import kotlinx.android.synthetic.main.badgeslist.view.*

class badgesAdapter (private var listbadges:List<Int>) : RecyclerView.Adapter<badgesAdapter.ViewHolder>(){

    class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        val imgshop : ImageView = item.badge

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): badgesAdapter.ViewHolder {
        val resultListLead = LayoutInflater.from(parent.context).inflate(R.layout.badgeslist , parent , false)
        return badgesAdapter.ViewHolder(resultListLead)
    }

    override fun onBindViewHolder(holder: badgesAdapter.ViewHolder, position: Int) {
        if(listbadges[position] == 1){
            holder.imgshop.setImageResource(R.drawable.achieve2)

        }
        if(listbadges[position] == 2){
            holder.imgshop.setImageResource(R.drawable.achieve3)

        }
        if(listbadges[position] == 3){
            holder.imgshop.setImageResource(R.drawable.achieve1)

        }
        if(listbadges[position] == 4){
            holder.imgshop.setImageResource(R.drawable.coin)

        }
    }

    override fun getItemCount(): Int {
        return listbadges.size
    }

}