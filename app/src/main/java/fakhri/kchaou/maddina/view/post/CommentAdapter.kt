package fakhri.kchaou.maddina.view.post

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.model.entity.Comment

class CommentAdapter(val context: Context, val comments : ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(data : Comment){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(comments.get(position))
    }

    override fun getItemCount(): Int {
      return comments.size
    }
}