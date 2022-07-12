package fakhri.kchaou.maddina.view.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.utils.convert
import fakhri.kchaou.maddina.view.post.PostItemActivity
import fakhri.kchaou.maddina.view.profile.ProfilFriendActivity


class HomeAdapter(val context: Context, val posts: ArrayList<Post>):
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

   inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){



        fun bindView(data : Post){


            val sharedPreferences: SharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
            val id = sharedPreferences.getString("userId", "")!!

            itemView.findViewById<TextView>(R.id.created_at).text = convert(data.created_at!!)
            itemView.findViewById<TextView>(R.id.post_text).text = data.text
            itemView.findViewById<TextView>(R.id.username).text = data.user?.name

                Glide.with(context /* context */)
                    .load(data.media_url)
                    .into(itemView.findViewById<ImageView>(R.id.post_img))

            itemView.setOnClickListener {


            }

            itemView.setOnClickListener{
                context?.let{
                    val intent = Intent (it, PostItemActivity::class.java)
                    intent.putExtra("postId", data.id);
                    it.startActivity(intent)
                }
            }

            itemView.findViewById<TextView>(R.id.username).setOnClickListener {

                if(!data.user?.id.equals(id)) {

                    context?.let{
                        val intent = Intent (it, ProfilFriendActivity::class.java)
                        intent.putExtra("friendId", data.user?.id);
                        it.startActivity(intent)
                    }
                }
                else{
                    context?.let{
                        HomeActivity.homeActivity?.finish()
                        val intent = Intent (it, HomeActivity::class.java)
                        intent.putExtra("fragment", "profile");
                        it.startActivity(intent)

                    }

                }

            }

            itemView.findViewById<ImageView>(R.id.profile_image).setOnClickListener {
                if(!data.user?.id.equals(id)) {

                    context?.let{
                        val intent = Intent (it, ProfilFriendActivity::class.java)
                        intent.putExtra("friendId", data.user?.id);
                        it.startActivity(intent)
                    }
                }
                else{
                    context?.let{
                        HomeActivity.homeActivity?.finish()
                        val intent = Intent (it, HomeActivity::class.java)
                        intent.putExtra("fragment", "profile");
                        it.startActivity(intent)

                    }

                }
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.post_item, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(posts[position])
    }

    override fun getItemCount(): Int {
       return posts.size
    }





}