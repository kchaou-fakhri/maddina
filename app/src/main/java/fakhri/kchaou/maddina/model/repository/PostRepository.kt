package fakhri.kchaou.maddina.model.repository


import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import fakhri.kchaou.maddina.model.entity.Post
import java.util.*


class PostRepository {

        private val database = Firebase.database
        private val db_reference = database.getReference("posts")
        private val mutableLiveData = MutableLiveData<Boolean>()
        private val storage_referance = FirebaseStorage.getInstance().getReference()


        fun createPost(id: String, text: String, uriImage: Uri?) : LiveData<Boolean>{
            try {
                val post = Post( id, text)
                if( uriImage == null){

                    db_reference.push().setValue(post)
                    mutableLiveData.value = true
                }
                else {

                    val ref = storage_referance.child("post/${id+Date().toString().replace(" ","") }")

                    var uploadTask=  ref.putFile(uriImage)

                    val urlTask = uploadTask.continueWithTask { task ->
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


}