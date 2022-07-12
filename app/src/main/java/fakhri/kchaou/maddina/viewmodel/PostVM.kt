package fakhri.kchaou.maddina.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.model.data.repository.PostRepository

class PostVM : ViewModel() {

    val postRepository = PostRepository()

    fun createPost(user : User, text: String, uriImage: Uri?) : LiveData<Boolean>{

        return postRepository.createPost( user, text, uriImage)
    }

    fun getPosts(userId : String) : LiveData<ArrayList<Post>>{
       return postRepository.getPosts(userId)
    }

    fun getPostById(postID: String?) : LiveData<Post> {

        return postRepository.getPostById(postID)
    }


}