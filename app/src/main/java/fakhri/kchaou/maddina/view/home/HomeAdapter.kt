package fakhri.kchaou.maddina.view.home

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.model.entity.Post
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




    fun convert (date : String) : String{

        var timeTxt = "ggggg"

        val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter).toString()

        if(date.substring(0,10).equals(formatted.substring(0,10)))
        {
            if (date.substring(11,13).equals(formatted.substring(11,13)))
            {
                val time = formatted.substring(14,16).toInt() - date.substring(14,16).toInt()
                when(time) {
                    0 -> timeTxt = "الأن "

                    1 -> timeTxt = "منذ دقيقة"
                    2 -> timeTxt = "منذ دقيقتان"

                    3 -> timeTxt = "منذ ثلاث دقائق"

                    4 -> timeTxt = "منذ أربع دقائق"

                    5 -> timeTxt = "منذ خمس دقائق"

                    10 -> timeTxt = "منذ عشرة دقائق"
                    else-> timeTxt = " منذ $time دقيقة "


                }



            }
            else{
                val time =formatted.substring(11,13).toInt() - date.substring(11,13).toInt()
                when(time){

                    1-> timeTxt ="منذ ساعة"
                    2-> timeTxt ="منذ سعتان"
                    3-> timeTxt ="منذ ثلاث ساعة"
                    4-> timeTxt ="منذ أربع ساعات"
                    5-> timeTxt ="منذ خمس ساعات"
                    6-> timeTxt ="منذ ست ساعات"
                    7-> timeTxt ="منذ سبع ساعات"
                    8-> timeTxt ="منذ ثماني ساعات"
                    9-> timeTxt =" منذ تسع ساعات"
                    10-> timeTxt ="منذ عشر ساعات"
                    11-> timeTxt =" $time منذ ساعات "


                }
            }

        }
        else{
            if (date.substring(0,4).equals(formatted.substring(0,4))){
                if (date.substring(5,7).equals(formatted.substring(5,7))){

                    if (date.substring(8,10).equals(formatted.substring(8,10))){
                        timeTxt = " منذ يوم "
                    }
                    else{
                    val time = formatted.substring(8,10).toInt() - date.substring(8,10).toInt()
                    timeTxt = " منذ $time يوم "
                }
                }
                else{
                    val time = formatted.substring(5,7).toInt() - date.substring(5,7).toInt()
                    timeTxt = " منذ $time شهر "
                }
            }
            else{
                val time = formatted.substring(0,4).toInt() - date.substring(0,4).toInt()
                when(time){
                     1-> timeTxt =  "منذ سنة"
                     2-> timeTxt = "منذ سنتين"
                     3-> timeTxt = "منذ ثلاث سنوات"
                     4-> timeTxt = "منذ أربع سنوات"
                     5-> timeTxt = "منذ خمس سنوات"
                     6-> timeTxt = "منذ ست سنوات"
                     7-> timeTxt = "منذ سبع سنوات"
                     8-> timeTxt = "منذ ثماني سنوات"
                     9-> timeTxt = "منذ تسع سنوات"
                     10-> timeTxt ="منذ عشرة سنوات"
                     11-> timeTxt = " منذ $time سنوات "


                }
            }
        }


        return timeTxt
    }

}