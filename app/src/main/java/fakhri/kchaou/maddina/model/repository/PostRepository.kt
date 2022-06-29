package fakhri.kchaou.maddina.model.repository


import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.model.repository.remote.PostRemote
import java.util.*
import kotlin.collections.ArrayList


class PostRepository() {
     var  postRemote: PostRemote = PostRemote()


    fun createPost(user: User, text: String, uriImage: Uri?): LiveData<Boolean> {
        return postRemote.createPost(user,text, uriImage)
    }

    fun getPosts(userId : String): LiveData<ArrayList<Post>> {
        return postRemote.getPosts(userId)
    }

    fun getPostById(postID: String?): LiveData<Post> {
        return postRemote.getPostById(postID)
    }


}