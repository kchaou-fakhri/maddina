package fakhri.kchaou.maddina.model.repository.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fakhri.kchaou.maddina.model.entity.Friend
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.utils.Message
import java.util.*

class UserRemote (){
    private val database = Firebase.database
    private val db_reference = database.getReference("users")
    private lateinit var auth: FirebaseAuth

    init {

    }






    fun insertTORealTimeDataBase(user: User){

        user.password =""

        val refDB = database.getReference("users")

        refDB.child(user.id.toString()).setValue(user)

    }

    fun logout(){
        auth = Firebase.auth
        auth.signOut()
    }

    fun ifLogin() :String? {

        auth = Firebase.auth

        var user = auth.currentUser

        if (user != null) {
            return  user.uid
        }

        return null
    }


    fun add(user: User)  : LiveData<Message>{

        var mutableLiveData = MutableLiveData<Message>()
        var message :Message

        try {

            auth = Firebase.auth
            auth.createUserWithEmailAndPassword(user.email!!, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        user.id = auth.currentUser?.uid


                        /**********Insert User to realTime database **************/
                        insertTORealTimeDataBase(user)
                        /**********End Insert User to realTime database **********/

                        message = Message(true, user.id.toString())
                        mutableLiveData.value = message


                    } else {
                        val  msg = task.exception.toString()

                        if(msg.length.equals(116)){
                            message = Message(false, "الحساب موجود بالفعل ")
                            mutableLiveData.value = message
                        }
                        else
                        {
                            message = Message(false, "حدث خطأ ما, حاول لاحقا")
                            mutableLiveData.value = message
                        }



                    }
                }
        }
        catch (e:Exception){
            message = Message(false, e.message.toString())
            mutableLiveData.value = message
        }

        return mutableLiveData
    }




    fun login(email: String, password: String) : LiveData<Message>{

        var mutableLiveData = MutableLiveData<Message>()
        var message :Message



        try {
            auth = Firebase.auth
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    /********** GET id of current user ***********************/
                    message = Message(true, task.result.user?.uid.toString())
                    mutableLiveData.value = message
                    /********** End GET id of current user *******************/


                }
            }.addOnFailureListener { exception ->
                message = Message(false, "المعلومات التي أدخلتها غير صحيحة")
                mutableLiveData.value = message


            }

        }catch(e :Exception){

            message = Message(false, "حدث خطأ ما, حاول لاحقا")
            mutableLiveData.value = message

        }

        return mutableLiveData

    }


    fun getUserById(id: String) : LiveData<User>{
        var mutableLiveData = MutableLiveData<User>()

        db_reference.child(id).get().addOnSuccessListener {
           //Log.println(Log.ASSERT,"firebase--------------------------------------", "Got value ${it.value}")
           val td: Map<String, Objects> = it.value as Map<String, Objects>

         mutableLiveData.value = it.getValue(User::class.java)
           // mutableLiveData.value?.relations = td.get("relation") as MutableList<Friend>


        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
            mutableLiveData.value = null
        }

        return mutableLiveData
    }



    fun updateUser(user: User): LiveData<Message> {

        var mutableLiveData = MutableLiveData<Message>()
        val refDB = database.getReference("users")

        refDB.child(user.id.toString()).setValue(user)
            .addOnSuccessListener {
              mutableLiveData.value = Message(true, "")
            }
            .addOnFailureListener {
                mutableLiveData.value = Message(false, "")
            }
        return mutableLiveData
    }

    fun addFriend(user: User, id: String): LiveData<Message> {
        var mutableLiveData = MutableLiveData<Message>()
        val refDB = database.getReference("users")

        refDB.child(user.id.toString()).child("relation").setValue(user.relations)

            refDB.child(id.toString()).child("relation").setValue(getSender(id, user))
            .addOnSuccessListener {
                mutableLiveData.value = Message(true, "yemchi")
            }
            .addOnFailureListener {
                mutableLiveData.value = Message(false, "laaaaaaaaaaaa")
            }
        return mutableLiveData
    }

    private fun getSender(id : String, user: User): MutableList<Friend>? {

       var i=0
        if (i in 0..user.relations!!.size) { // equivalent of 1 <= i && i <= 4
            if (user.relations!!.get(i).id.equals(id)){
                user.relations!!.get(i).id = user.id
                user.relations!!.get(i).state = "+follower"


            }
        }

        return user.relations

    }

}