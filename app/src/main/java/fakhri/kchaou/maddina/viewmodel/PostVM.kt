package fakhri.kchaou.maddina.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fakhri.kchaou.maddina.model.repository.PostRepository

class PostVM : ViewModel() {

    val postRepository = PostRepository()

    fun createPost(id: String, text: String, uriImage: Uri?) : LiveData<Boolean>{

        return postRepository.createPost( id, text, uriImage)
    }




}