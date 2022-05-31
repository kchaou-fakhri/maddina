package fakhri.kchaou.maddina.model.repository


import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.model.repository.remote.PostRemote
import java.util.*
import kotlin.collections.ArrayList


class PostRepository() {
    lateinit var  postRemote: PostRemote

    init {
        postRemote = PostRemote()
    }
    fun createPost(id: String, text: String, uriImage: Uri?): LiveData<Boolean> {
        return postRemote.createPost(id,text, uriImage)
    }

    fun getPost(): LiveData<ArrayList<Post>> {
        return postRemote.getPost()
    }


}