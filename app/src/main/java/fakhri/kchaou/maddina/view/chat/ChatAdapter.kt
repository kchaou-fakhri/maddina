package fakhri.kchaou.maddina.view.chat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.model.entity.Chat
import fakhri.kchaou.maddina.model.entity.User

class ChatAdapter(val context: Context, var chats : ArrayList<Chat>)
    : RecyclerView.Adapter<ChatAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val sharedPreferences: SharedPreferences? = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val userId :String = sharedPreferences?.getString("userId", "").toString()
        fun bind(item : Chat){

            itemView.findViewById<TextView>(R.id.username).text = "item.id"
            itemView.findViewById<ImageView>(R.id.user_image).setOnClickListener {
                context?.let{
                    val intent = Intent (it, ChatActivity::class.java )
                    if (userId == item.usersID.get(0)){
                        intent.putExtra("friendId", item.usersID.get(1));
                        intent.putExtra("friendName", " ");
                    }else
                    {
                        intent.putExtra("friendId", item.usersID.get(0));
                        intent.putExtra("friendName", " ");
                    }

                    it.startActivity(intent)
                }
            }



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}