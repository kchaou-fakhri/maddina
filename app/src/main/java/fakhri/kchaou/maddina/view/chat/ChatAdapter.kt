package fakhri.kchaou.maddina.view.chat

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.model.entity.Chat
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.viewmodel.UserVM

class ChatAdapter(val context: Context, var chats : ArrayList<Chat>)
    : RecyclerView.Adapter<ChatAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val lifecycleOwner by lazy{
            context as? LifecycleOwner
        }
        val sharedPreferences: SharedPreferences? = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val userId :String = sharedPreferences?.getString("userId", "").toString()



        @SuppressLint("SuspiciousIndentation")
        fun bind(item : Chat){
        var friendID = ""
            var friendName = ""
            val userVM = UserVM()


            if (userId == item.usersID.get(0)){
                friendID = item.usersID.get(1)
            }else
            {
                friendID = item.usersID.get(0)

            }

            Log.println(Log.ASSERT, "///////////-------", friendID)

            userVM.getUserById(friendID).observe(lifecycleOwner!!, Observer {


                itemView.findViewById<TextView>(R.id.username).text = it.name
                friendName = it.name.toString()

                if (!it.userImage.isNullOrEmpty()){
                    Glide.with(context /* context */)
                        .load(it.userImage)
                        .into(itemView.findViewById<ImageView>(R.id.user_image))
                }else{
                    Glide.with(context /* context */)
                        .load(R.drawable.img_if_no_user_image)
                        .into(itemView.findViewById<ImageView>(R.id.user_image))
                }
            })

            itemView.findViewById<ImageView>(R.id.user_image).setOnClickListener {
                context?.let{
                    val intent = Intent (it, ChatActivity::class.java )

                        intent.putExtra("friendId", friendID);
                        intent.putExtra("friendName", friendName);



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