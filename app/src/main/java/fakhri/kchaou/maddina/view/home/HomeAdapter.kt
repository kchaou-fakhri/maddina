package fakhri.kchaou.maddina.view.home

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.model.entity.Post


class HomeAdapter(val context: Context, val posts: ArrayList<Post>):
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindView(data : Post){
            itemView.findViewById<TextView>(R.id.post_text).text = data.text

            if(data.media_url != null){
                Glide.with(context /* context */)
                    .load(data.media_url)
                    .into(itemView.findViewById<ImageView>(R.id.post_img))
                var displayMetrics = DisplayMetrics()






            }
            else
            {
                itemView.findViewById<ImageView>(R.id.post_img).layoutParams.height = 0

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