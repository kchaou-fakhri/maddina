package fakhri.kchaou.maddina.viewmodel

import fakhri.kchaou.maddina.model.repository.PostRepository

class PostVM {

    val postRepository = PostRepository()

    fun createPost( id: String, text: String){

        postRepository.createPost( id, text)



    }
}