package fakhri.kchaou.maddina.view.post

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.ActivityPostItemBinding
import fakhri.kchaou.maddina.model.entity.Comment
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.utils.AlertNoConnection
import fakhri.kchaou.maddina.utils.LoadingAlert
import fakhri.kchaou.maddina.utils.convert
import fakhri.kchaou.maddina.view.home.HomeActivity
import fakhri.kchaou.maddina.view.profile.ProfilFriendActivity
import fakhri.kchaou.maddina.viewmodel.PostVM

class PostItemActivity : AppCompatActivity() {

    lateinit var binding : ActivityPostItemBinding
    lateinit var postVM: PostVM

    lateinit var post : Post



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostItemBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)

        val sharedPreferences: SharedPreferences? = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        val userId :String = sharedPreferences?.getString("userId", "").toString()


        postVM = PostVM()
        val loadingAlert = LoadingAlert(this)
        loadingAlert.startLoadingAlert()
        val postID = intent.extras!!.getString("postId")

        postVM.getPostById(postID).observe(this, Observer {
            if (it!= null) {

                post = it
                binding.postText.text = it.text
                binding.username.text = it.user?.name
                binding.createdAt.text = convert(it.created_at!!)
                Glide.with(this /* context */)
                    .load(it.media_url)
                    .into(findViewById<ImageView>(R.id.post_img))
                loadingAlert.dismissDialog()
            }

            else{
                val noConnection = AlertNoConnection(this)
                noConnection.startLoadingAlert()

            }


        })

        var temComments = arrayListOf<Comment>(Comment(), Comment(), Comment() , Comment() ,Comment(), Comment(), Comment(), Comment() , Comment() ,Comment())

        val adapter = CommentAdapter(this,temComments )
        binding.comments.layoutManager = LinearLayoutManager(this)
        binding.comments.adapter = adapter


        binding.username.setOnClickListener {
            if(!post.user?.id.equals(userId)) {

                val intent = Intent(this, ProfilFriendActivity::class.java)
                intent.putExtra("friendId", post.user?.id);
                startActivity(intent)
            }
            else{
                HomeActivity.homeActivity?.finish()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("fragment", "profile");
                startActivity(intent)
                this.finish()
            }
        }

        binding.profileImage.setOnClickListener {
            if(!post.user?.id.equals(userId)) {

                val intent = Intent(this, ProfilFriendActivity::class.java)
                intent.putExtra("friendId", post.user?.id);
                startActivity(intent)
            }
            else{
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("fragment", "profile");
                startActivity(intent)
                this.finish()
            }
        }
    }
}