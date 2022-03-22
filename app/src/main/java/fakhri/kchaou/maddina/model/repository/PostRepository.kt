package fakhri.kchaou.maddina.model.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fakhri.kchaou.maddina.model.entity.Post


class PostRepository {

        private val database = Firebase.database
        private val db_reference = database.getReference("posts")


        fun createPost(id: String, text: String){
            val post = Post( id, text)

            db_reference.push().setValue(post)

        }


}