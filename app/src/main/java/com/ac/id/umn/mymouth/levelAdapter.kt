package com.ac.id.umn.mymouth

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.levellist.view.*
import kotlinx.android.synthetic.main.shoplist.view.*

class levelAdapter (private var listlvl:List<Int>,
                    private val levelpact : Activity,
                    private var activeAcc: String): RecyclerView.Adapter<levelAdapter.ViewHolder>(){

    class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        val lvlcard : CardView = item.btnpicklvl
        val lvltxt : TextView = item.txtlvl

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): levelAdapter.ViewHolder {
        val resultListLead = LayoutInflater.from(parent.context).inflate(R.layout.levellist , parent , false)
        return levelAdapter.ViewHolder(resultListLead)
    }

    override fun onBindViewHolder(holder: levelAdapter.ViewHolder, position: Int) {
        if((listlvl[position] + 1) == 1){
            holder.lvltxt.text = "Pengenalan Mulut "
        }
        if((listlvl[position] + 1) == 2){
            holder.lvltxt.text = "Karies Gigi"
        }
        if((listlvl[position] + 1) == 3){
            holder.lvltxt.text = "Gigi"
        }
        if((listlvl[position] + 1) == 4){
            holder.lvltxt.text = "Anomali Gigi"
        }
        if((listlvl[position] + 1) == 5){
            holder.lvltxt.text = "Bibir"
        }
        if((listlvl[position] + 1) == 6){
            holder.lvltxt.text = "Kelainan Bibir"
        }
        if((listlvl[position] + 1) == 7){
            holder.lvltxt.text = "Lidah"
        }
        if((listlvl[position] + 1) == 8){
            holder.lvltxt.text = "Kelainan Lidah"
        }
        if((listlvl[position] + 1) == 9){
            holder.lvltxt.text = ""
        }
        if((listlvl[position] + 1) == 10){
            holder.lvltxt.text = ""
        }



        if(!activeAcc.contains(listlvl[position].toString())){
            holder.lvlcard.setCardBackgroundColor(Color.parseColor("#72071A"))
            holder.lvlcard.isClickable = false
        }
        else{
            holder.lvlcard.setOnClickListener {
                /*Toast.makeText( levelpact, "asda", Toast.LENGTH_SHORT).show()*/
                val intent = Intent (levelpact, GameplayActivity::class.java)
                intent.putExtra("Status", (listlvl[position] + 1))
                levelpact?.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return listlvl.size
    }
}