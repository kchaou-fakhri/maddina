package fakhri.kchaou.maddina.model.data.repository


import android.net.Uri
import androidx.lifecycle.LiveData
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.model.data.remote.PostRemote
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