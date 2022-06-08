package fakhri.kchaou.maddina.view.home

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.model.entity.Comment
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.utils.convert
import fakhri.kchaou.maddina.view.post.PostItemActivity
import fakhri.kchaou.maddina.view.profile.Profil_Friend_Activity
import fakhri.kchaou.maddina.view.profile.edit.EditProfilActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class HomeAdapter(val context: Context, val posts: ArrayList<Post>):
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){



        fun bindView(data : Post){

            itemView.findViewById<TextView>(R.id.created_at).text = convert(data.created_at!!)
            itemView.findViewById<TextView>(R.id.post_text).text = data.text
            itemView.findViewById<TextView>(R.id.username).text = data.user?.name

                Glide.with(context /* context */)
                    .load(data.media_url)
                    .into(itemView.findViewById<ImageView>(R.id.post_img))

            itemView.setOnClickListener {
                context?.let{
                    val intent = Intent (it, PostItemActivity::class.java)
                    intent.putExtra("postId", data.id );
                    it.startActivity(intent)
                }
            }

            itemView.findViewById<TextView>(R.id.username).setOnClickListener {
                context?.let{
                    val intent = Intent (it, Profil_Friend_Activity::class.java)
                    intent.putExtra("friendId", data.user?.id );
                    it.startActivity(intent)
                }
            }

            itemView.findViewById<ImageView>(R.id.profile_image).setOnClickListener {
                context?.let{
                    val intent = Intent (it, Profil_Friend_Activity::class.java)
                    intent.putExtra("friendId", data.user?.id );
                    it.startActivity(intent)
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