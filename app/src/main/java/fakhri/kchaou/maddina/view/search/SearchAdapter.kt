package fakhri.kchaou.maddina.view.search

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.model.entity.Friend
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.view.friend.ListFriendAdapter
import fakhri.kchaou.maddina.viewmodel.UserVM

class SearchAdapter(val context : Context, val friends: ArrayList<User>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val lifecycleOwner by lazy{
            context as? LifecycleOwner
        }
        fun bindView(data : User){
            itemView.findViewById<TextView>(R.id.username).text = data.name

            itemView.findViewById<TextView>(R.id.btn_accepted).setOnClickListener {
                val userVM = UserVM()
                val sharedPreferences: SharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
                val id = sharedPreferences.getString("userId", "")!!



                userVM.getUserById(id).observe(lifecycleOwner!!,Observer {
                    var friend = data
                    it.relations?.remove(Friend(data.id, "+follower"))
                    it.relations?.add(Friend(data.id, "friend"))

                    friend.relations?.remove(Friend(it.id, "-follower"))
                    friend.relations?.add(Friend(it.id, "friend"))

                    userVM.acceptedFriend(it, friend).observe(lifecycleOwner!!, Observer {
                        val mySnackbar = Snackbar.make(itemView.findViewById(R.id.main), "تهنينا, لقد أصبحت صديقا مع ${data.name}", Snackbar.LENGTH_LONG)
                        mySnackbar.setDuration(2000)


                        deleteItem(position)

                        mySnackbar.show()

                    })
                })




            }

            itemView.findViewById<TextView>(R.id.cancel).setOnClickListener {
                val userVM = UserVM()
                val sharedPreferences: SharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
                val id = sharedPreferences.getString("userId", "")!!



                userVM.getUserById(id).observe(lifecycleOwner!!, Observer {
                    var friend = data
                    it.relations?.remove(Friend(data.id, "+follower"))


                    friend.relations?.remove(Friend(it.id, "-follower"))


                    userVM.acceptedFriend(it, friend).observe(lifecycleOwner!!, Observer {
                        val mySnackbar = Snackbar.make(itemView.findViewById(R.id.main), "تم ازالت طلب الصداقة", Snackbar.LENGTH_LONG)
                        mySnackbar.setDuration(2000)


                        deleteItem(position)

                        mySnackbar.show()

                    })
                })




            }

//            Glide.with(context /* context */)
//                .load(data.image)
//                .into(itemView.findViewById<ImageView>(R.id.profile_image))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.search_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(friends.get(position))
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    fun deleteItem(index: Int){
        friends.removeAt(index)
        notifyDataSetChanged()
    }
}