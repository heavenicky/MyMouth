package com.ac.id.umn.mymouth

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.achievementlist.view.*
import kotlinx.android.synthetic.main.shoplist.view.*

class achievementAdapter  (      private var listachievement:List<userAchievement>,
                                 private var activeAcc: String): RecyclerView.Adapter<achievementAdapter.ViewHolder>() {

    class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        val imgshop : ImageView = item.ptcachievement
        val itemname : TextView = item.txtachievementdetail
        val viewcard : CardView = item.btnachievement1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): achievementAdapter.ViewHolder {
        val resultListLead = LayoutInflater.from(parent.context).inflate(R.layout.achievementlist , parent , false)
        return achievementAdapter.ViewHolder(resultListLead)
    }

    override fun onBindViewHolder(holder: achievementAdapter.ViewHolder, position: Int) {
        holder.itemname.text = listachievement[position].achievname
        holder.imgshop.setImageResource(listachievement[position].imagebit)

        if(!activeAcc.contains(listachievement[position].imgid.toString())){
            holder.viewcard.setCardBackgroundColor(Color.parseColor("#72071A"))
        }
    }

    override fun getItemCount(): Int {
        return listachievement.size
    }

}