package com.ac.id.umn.mymouth

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.avatarboxconfirm.view.*
import kotlinx.android.synthetic.main.avatarlist.view.*


class avatarAdapter (var piclist : List<avatarList>,
                     private val shopact : Activity,
                     private val firebase : FirebaseFirestore,
                     private val navController : NavController,
    ): RecyclerView.Adapter<avatarAdapter.ViewHolder>() {

        class ViewHolder(item: View):RecyclerView.ViewHolder(item){
            val nama: TextView = item.txtavatarname
            val avatar: ImageView = item.ptcprofile
            val shop2: CardView = item.btnpickavatar
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): avatarAdapter.ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.avatarlist, parent, false)
            return avatarAdapter.ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: avatarAdapter.ViewHolder, position: Int) {
            holder.nama.text = piclist[position].itemname
            holder.avatar.setImageResource(piclist[position].imagebit)
            holder.shop2.setOnClickListener {
                shopDialog(piclist[position].imgid,position)
            }
        }

        override fun getItemCount(): Int {
            return piclist.size
        }

        private fun shopDialog(id: Int, position: Int) {
            val mDialogView = LayoutInflater.from(shopact).inflate(R.layout.avatarboxconfirm, null)
            val mBuilder = AlertDialog.Builder(shopact)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mAlertDialog.window?.setBackgroundDrawable(
                AppCompatResources.getDrawable(
                    shopact,
                    R.drawable.orange
                )
            )

            mDialogView.dialog_yes_update.setOnClickListener {
                firebase.collection("ms_user")
                    .document(FirebaseAuth.getInstance().uid.toString())
                    .update("activePhoto", id)
                mAlertDialog.dismiss()
                navController.navigate(R.id.action_changeProfileFragment_to_profileFragment)

            }

            mDialogView.dialog_no_update.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }

