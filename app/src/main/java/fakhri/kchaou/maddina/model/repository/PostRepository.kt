package fakhri.kchaou.maddina.model.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fakhri.kchaou.maddina.model.entity.Post


class PostRepository {

        private val database = Firebase.database
        private val db_reference = database.getReference("posts")
        private val mutableLiveData = MutableLiveData<Boolean>()


        fun createPost(id: String, text: String) : LiveData<Boolean>{
            try {
                val post = Post( id, text)
                db_reference.push().setValue(post)

                 mutableLiveData.value = true
                return mutableLiveData

            }catch (e :Exception){
                mutableLiveData.value = false
                return mutableLiveData

            }

        }


}