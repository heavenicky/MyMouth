package com.ac.id.umn.mymouth

import android.app.Activity
import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.boxconfirm.view.*
import kotlinx.android.synthetic.main.leaderboardlist.view.*
import kotlinx.android.synthetic.main.shoplist.view.*

class shopAdapter (private var listShop:List<shopList>,
                   private val shopact : Activity,
                   private val firebase : FirebaseFirestore,
                   private var activeAcc:TextView): RecyclerView.Adapter<shopAdapter.ViewHolder>(){

    class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        val imgshop : ImageView = item.ptcshop
        val harga : TextView = item.txtprice
        val itemname : TextView = item.txtshopname
        val viewcard : CardView = item.btnpickshop
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): shopAdapter.ViewHolder {
        val resultListLead = LayoutInflater.from(parent.context).inflate(R.layout.shoplist , parent , false)
        return shopAdapter.ViewHolder(resultListLead)
    }

    override fun onBindViewHolder(holder: shopAdapter.ViewHolder, position: Int) {
        holder.itemname.text = listShop[position].itemname
        holder.harga.text = listShop[position].price.toString()
        holder.imgshop.setImageResource(listShop[position].imagebit)

        holder.viewcard.setOnClickListener {
            shopDialog(listShop[position].imgid,listShop[position].price,position)
        }

    }

    override fun getItemCount(): Int {
        return listShop.size
    }

    private fun shopDialog(id: Int, price: Int, position: Int) {
        var newPrice: Int = 0
        var ownedMoney: Int = 0
        var list1: ArrayList<Int> = ArrayList()
        var ListData: ArrayList<shopList> = ArrayList()
        val mDialogView = LayoutInflater.from(shopact).inflate(R.layout.boxconfirm, null)
        val mBuilder = AlertDialog.Builder(shopact)
                .setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        mAlertDialog.window?.setBackgroundDrawable(
                AppCompatResources.getDrawable(
                        shopact,
                        R.drawable.orange
                )
        )

        firebase.collection("ms_user")
                .document(FirebaseAuth.getInstance().uid.toString())
                .get().addOnSuccessListener {
                    val myPfpList = it.get("photoInvent")
                    newPrice = it.getLong("money")?.minus(price)!!.toInt()
                    ownedMoney = it.getLong("money")!!.toInt()
                    Log.e("List", myPfpList.toString())
                    list1.add(1)
                    if(myPfpList.toString().contains("2")){
                        list1.add(2)
                    }
                    if(myPfpList.toString().contains("3")){
                        list1.add(3)
                    }
                    if(myPfpList.toString().contains("4")){
                        list1.add(4)
                    }
                    if(myPfpList.toString().contains("5")){
                        list1.add(5)
                    }
                    if(myPfpList.toString().contains("6")){
                        list1.add(6)
                    }
                    if(myPfpList.toString().contains("7")){
                        list1.add(7)
                    }
                    if(myPfpList.toString().contains("8")){
                        list1.add(8)
                    }
                    if(myPfpList.toString().contains("9")){
                        list1.add(9)
                    }
                    list1.add(id)
                }

        mDialogView.dialog_yes_upgrade.setOnClickListener {

            if (price > ownedMoney){
                Toast.makeText(shopact,"Not Enough Coin", Toast.LENGTH_SHORT).show();
            }else{
                firebase.collection("ms_user")
                        .document(FirebaseAuth.getInstance().uid.toString())
                        .update("photoInvent", list1,
                                "money", newPrice)

                firebase.collection("ms_user")
                        .document(FirebaseAuth.getInstance().uid.toString())
                        .get().addOnSuccessListener {
                            val getList = it.get("photoInvent")
                            Log.e("List", getList.toString())
                            if(!getList.toString().contains("2")){
                                ListData.add(shopList("Baby Sick", 100, R.drawable.babysick, 2))
                            }
                            if(!getList.toString().contains("3")){
                                ListData.add(shopList("Dentist", 1000, R.drawable.dentist, 3))
                            }
                            if(!getList.toString().contains("4")){
                                ListData.add(shopList("Male Nurse", 750, R.drawable.malenurse, 4))
                            }
                            if(!getList.toString().contains("5")){
                                ListData.add(shopList("Nurse", 750, R.drawable.nurse, 5))
                            }
                            if(!getList.toString().contains("6")){
                                ListData.add(shopList("Mouth", 200, R.drawable.mouth, 6))
                            }
                            if(!getList.toString().contains("7")){
                                ListData.add(shopList("Toothbrush", 150, R.drawable.toothbrush, 7))
                            }
                            if(!getList.toString().contains("8")){
                                ListData.add(shopList("Tooth", 150, R.drawable.tooth, 8))
                            }
                            if(!getList.toString().contains("9")){
                                ListData.add(shopList("Table", 500, R.drawable.operationtable, 9))
                            }
                            listShop = ListData
                            activeAcc.text = it.getLong("money").toString()
                            notifyDataSetChanged()
                        }
            }
            mAlertDialog.dismiss()
        }

        mDialogView.dialog_no_upgrade.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }
}