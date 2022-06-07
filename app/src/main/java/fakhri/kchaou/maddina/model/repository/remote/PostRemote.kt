package fakhri.kchaou.maddina.model.repository.remote

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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class PostRemote {

    private val database = Firebase.database
    private val db_reference = database.getReference("posts")
    private val storage_referance = FirebaseStorage.getInstance().getReference()


    @RequiresApi(Build.VERSION_CODES.O)
    fun createPost(user : User, text: String, uriImage: Uri?) : LiveData<Boolean> {
        val mutableLiveData = MutableLiveData<Boolean>()


        try {
            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted = current.format(formatter)
            val post = Post( user, text,"", formatted.toString())
            if( uriImage == null){

                db_reference.push().setValue(post)
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
                        db_reference.push().setValue(post)
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

        db_reference.get().addOnSuccessListener {
            //  Log.i("firebase", "Got value ${it.value}")
            var post : Post
            for(item in it.children){


                post = item.getValue(Post::class.java)!!
                posts.add(post)

            }





            mutableLiveData.value = posts

        }.addOnFailureListener{
            mutableLiveData.value = null
            Log.e("firebase", "Error getting data", it)
        }

        return mutableLiveData
    }
}