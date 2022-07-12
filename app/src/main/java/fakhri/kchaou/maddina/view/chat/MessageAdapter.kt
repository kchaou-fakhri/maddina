package fakhri.kchaou.maddina.view.chat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.model.entity.Message
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.view.profile.ProfilFriendActivity

class MessageAdapter(val context: Context, var messages : ArrayList<Message>)
    : RecyclerView.Adapter<MessageAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val sharedPreferences: SharedPreferences? = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val userId :String = sharedPreferences?.getString("userId", "").toString()

        fun bind(item :Message){

            if (item.senderID == userId ){
                itemView.findViewById<ConstraintLayout>(R.id.constraint_sender).visibility = View.VISIBLE
                itemView.findViewById<TextView>(R.id.sender).text = item.text
            }else{
                itemView.findViewById<ConstraintLayout>(R.id.constraint_receiver).visibility = View.VISIBLE
                itemView.findViewById<TextView>(R.id.receiver).text = item.text
            }



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.message_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int {
        return messages.size
    }
}