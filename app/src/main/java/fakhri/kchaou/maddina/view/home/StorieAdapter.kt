package fakhri.kchaou.maddina.view.home
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.model.entity.Post


class StoriesAdapter(val context: Context,val posts :ArrayList<Post>):
    RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindView(data: Post){

//            Glide.with(context /* context */)
//                .load(data.media_url)
//                .into(itemView.findViewById<ImageView>(R.id.user_image))

                if (position == 0){
                    Glide.with(context)
                        .load(R.drawable.img_add)
                        .into(itemView.findViewById<ImageView>(R.id.story))

                    itemView.findViewById<TextView>(R.id.username).text = context.getString(R.string.create_post)
                    itemView.findViewById<TextView>(R.id.username).textSize = 16.0F

                }
                else{

                    Glide.with(context )
                        .load(data.media_url)
                        .into(itemView.findViewById<ImageView>(R.id.story))
                    Glide.with(context )
                        .load(R.drawable.user)
                        .into(itemView.findViewById<ImageView>(R.id.user_image))
                }




        //    itemView.findViewById<TextView>(R.id.username).text = data.user.name
//            itemView.setOnClickListener {
//                val intent = Intent(context, PictureFullScreen::class.java).apply {
//                    putExtra("name",data.user.name)
//                    putExtra("username",data.user.username)
//                    putExtra("photo",data.url.regular)
//                    putExtra("photo_user",data.user.profileImage.large)
//                }
//                context.startActivity(intent)
//            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.storie_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(posts.get(position))
    }

    override fun getItemCount(): Int {
        return posts.size
    }


}