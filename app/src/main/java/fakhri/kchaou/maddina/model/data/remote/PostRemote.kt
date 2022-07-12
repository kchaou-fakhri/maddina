package fakhri.kchaou.maddina.model.data.remote

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.model.entity.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class PostRemote {
    val db = Firebase.firestore

    private val storage_referance = FirebaseStorage.getInstance().getReference()


    @RequiresApi(Build.VERSION_CODES.O)
    fun createPost(user : User, text: String, uriImage: Uri?) : LiveData<Boolean> {
        val mutableLiveData = MutableLiveData<Boolean>()
        val userRemote = UserRemote()

        try {
            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted = current.format(formatter)
            val post = Post( user, text,"", formatted.toString())



            if( uriImage == null){

        val result=   db.collection("posts").document()
                val id = result.id
                        result.set(post)

                user.posts?.add(id)
                userRemote.addPostIDtoUser(user)
                mutableLiveData.value = true
            }
            else {

                val ref = storage_referance.child("post/${user.id+ Date().toString().replace(" ","") }")

                var uploadTask=  ref.putFile(uriImage)

                uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it

                        }
                    }
                    ref.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var downloadUri = task.result
                        post.media_url = downloadUri.toString()

                     var result = db.collection("posts").document()
                        val id = result.id
                         result.set(post)

                        user.posts?.add(id)
                        userRemote.addPostIDtoUser(user)
                        mutableLiveData.value = true

                    }
                }


            }

            return mutableLiveData

        }catch (e :Exception){
            mutableLiveData.value = false
            return mutableLiveData

        }

    }

    fun getPost() : LiveData<ArrayList<Post>> {
        val mutableLiveData = MutableLiveData<ArrayList<Post>>()
        var posts = ArrayList<Post>()
        db.collection("posts")
            .get()
            .addOnSuccessListener {  documents ->
            //  Log.i("firebase", "Got value ${it.value}")
            var post : Post

                for (document in documents) {
                    post = document.toObject(Post::class.java)

                    post.id = document.id
                    posts.add(post)
                }

                mutableLiveData.value = posts
            }







        .addOnFailureListener{
            mutableLiveData.value = null
            Log.e("firebase", "Error getting data", it)
        }

        return mutableLiveData
    }

    fun getPostById(postID: String?): LiveData<Post> {
        var mutableLiveData = MutableLiveData<Post>()
        db.collection("posts").document(postID!!)
            .get()
            .addOnSuccessListener {
            //   Log.println(Log.ASSERT,"firebase--------------------------------------", "Got value ${it.getValue()}")
            // val td: Map<String, String> = it.value as Map<String, String>

            mutableLiveData.value = it.toObject(Post::class.java)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
            mutableLiveData.value = null
        }

        return mutableLiveData
    }

    fun getPosts(userId: String) : LiveData<ArrayList<Post>> {
        val mutableLiveData = MutableLiveData<ArrayList<Post>>()
        var posts = ArrayList<Post>()


        db.collection("users").document(userId).get().addOnSuccessListener {
            var idUsers = ArrayList<String>()
            it.toObject(User::class.java)?.relations?.forEach {
                if (it.state.equals("friend")){
                    idUsers.add(it.id!!)
                }
            }
          idUsers.add(userId)

                db.collection("posts")
                    .whereIn("user.id", idUsers)
                    .get()
                    .addOnSuccessListener { documents ->
                        //  Log.i("firebase", "Got value ${it.value}")
                        var post: Post

                        for (document in documents) {
                            post = document.toObject(Post::class.java)

                            post.id = document.id
                            posts.add(post)
                        }

                        mutableLiveData.value = posts
                    }
                    .addOnFailureListener {
                        mutableLiveData.value = null
                        Log.e("firebase", "Error getting data", it)
                    }
            }

        return mutableLiveData
    }


}