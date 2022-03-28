package fakhri.kchaou.maddina.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.model.repository.PostRepository

class PostVM() : ViewModel() {

    val postRepository = PostRepository()

    fun createPost(user: User, text: String, uriImage: Uri?) : LiveData<Boolean>{


        return postRepository.createPost(user,  text, uriImage)
    }

    fun getPost() : LiveData<ArrayList<Post>>{
       return postRepository.getPost()
    }




}