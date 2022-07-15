package fakhri.kchaou.maddina.view.chat

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.view.profile.ProfilFriendActivity

class UsersConnectedAdapter(val context: Context, var users : ArrayList<User>)
    : RecyclerView.Adapter<UsersConnectedAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item : User){
            itemView.findViewById<TextView>(R.id.username).text = item.name
            itemView.findViewById<ImageView>(R.id.user_image).setOnClickListener {
                context?.let{
                    val intent = Intent (it, ChatActivity::class.java )
                    intent.putExtra("friendId", item?.id);
                    intent.putExtra("friendName", item?.name);
                    it.startActivity(intent)
                }
            }



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_connected_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }
}