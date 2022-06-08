package fakhri.kchaou.maddina.view.post

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.ActivityPostItemBinding
import fakhri.kchaou.maddina.model.entity.Comment
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.utils.AlertNoConnection
import fakhri.kchaou.maddina.utils.LoadingAlert
import fakhri.kchaou.maddina.utils.convert
import fakhri.kchaou.maddina.view.home.HomeActivity
import fakhri.kchaou.maddina.view.profile.Profil_Friend_Activity
import fakhri.kchaou.maddina.viewmodel.PostVM

class PostItemActivity : AppCompatActivity() {

    lateinit var binding : ActivityPostItemBinding
    lateinit var postVM: PostVM
    lateinit var user : User



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostItemBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)

        postVM = PostVM()
        val loadingAlert = LoadingAlert(this)
        loadingAlert.startLoadingAlert()
        val postID = intent.extras!!.getString("postId")

        postVM.getPostById(postID).observe(this, Observer {
            if (it!= null) {
                user = it.user!!
                binding.postText.text = it.text
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
            val intent = Intent(this, Profil_Friend_Activity::class.java)
            intent.putExtra("friendId",user?.id);
            startActivity(intent)
            this.finish()
        }

        binding.profileImage.setOnClickListener {
            val intent = Intent(this, Profil_Friend_Activity::class.java)
            intent.putExtra("friendId",user?.id);
            startActivity(intent)
            this.finish()
        }
    }
}