package fakhri.kchaou.maddina.model.repository.remote

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import fakhri.kchaou.maddina.model.entity.Friend
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.utils.Message
import java.util.*
import kotlin.collections.ArrayList

class  UserRemote  (){

    private lateinit var auth: FirebaseAuth




    fun insertTORealTimeDataBase(user: User){

        user.password =""
        val db = Firebase.firestore


        db.collection("users").document(user.id!!)
            .set(user)

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
        var users = ArrayList<User>()
        val db = Firebase.firestore
        db.collection("users").document(id)
            .get()
            .addOnSuccessListener {

                    document ->
                if (document != null) {
                    mutableLiveData.value = document.toObject(User::class.java)
                } else {
                    Log.d(TAG, "No such document")
                }


            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        return mutableLiveData
    }



    fun updateUser(user: User): LiveData<Message> {

        var mutableLiveData = MutableLiveData<Message>()
        val db = Firebase.firestore
        db.collection("users").document(user.id!!)
            .set(user)
            .addOnSuccessListener {
              mutableLiveData.value = Message(true, "")
            }
            .addOnFailureListener {
                mutableLiveData.value = Message(false, "")
            }
        return mutableLiveData
    }



    fun addFriend(user: User, friend: User): LiveData<Message> {
        var mutableLiveData = MutableLiveData<Message>()
        val db = Firebase.firestore
        db.collection("users").document(user.id!!).update(mapOf(
            "relations" to user.relations,
        ))

        db.collection("users").document(friend.id!!).update(mapOf(
            "relations" to friend.relations,
        ))
            .addOnSuccessListener {
                mutableLiveData.value = Message(true, "yemchi")
            }
            .addOnFailureListener {
                mutableLiveData.value = Message(false, "laaaaaaaaaaaa")
            }
        return mutableLiveData
    }



    fun getFrineds(id: String): LiveData<ArrayList<User>> {


        var mutableLiveData = MutableLiveData<ArrayList<User>>()
        val db = Firebase.firestore


        var idUsers = ArrayList<String>()
        db.collection("users").document(id).get().addOnSuccessListener {



            it.toObject(User::class.java)?.relations?.forEach {
                if (it.state.equals("+follower")){
                    idUsers.add(it.id!!)
                }
            }
            if (idUsers.isNotEmpty()){



            db.collection("users").whereIn("id", idUsers).get()
                .addOnSuccessListener {

                    var relations = ArrayList<User>()



                    for (item in it.documents) {

                        relations.add(item.toObject(User::class.java)!!)
                    }
                    mutableLiveData.value = relations
                }
        }

        }
        return mutableLiveData
    }

    fun getUsers(): LiveData<ArrayList<User>> {


        var mutableLiveData = MutableLiveData<ArrayList<User>>()
        val db = Firebase.firestore

        db.collection("users").get().addOnSuccessListener {

                        var relations = ArrayList<User>()

                        for (item in it.documents) {

                            relations.add(item.toObject(User::class.java)!!)
                        }
                        mutableLiveData.value = relations

            }


        return mutableLiveData
    }

    fun acceptedFriend(user: User, friend: User): LiveData<Message> {

        var mutableLiveData = MutableLiveData<Message>()
        val db = Firebase.firestore
        db.collection("users").document(user.id!!)
            .update(mapOf( "relations" to user.relations))


        db.collection("users").document(friend.id!!).update(mapOf(
            "relations" to friend.relations,
        ))
            .addOnSuccessListener {
                mutableLiveData.value = Message(true, "yemchi")
            }
            .addOnFailureListener {
                mutableLiveData.value = Message(false, "laaaaaaaaaaaa")
            }
        return mutableLiveData
    }



    fun addPostIDtoUser(user: User){
        val db = Firebase.firestore
        db.collection("users").document(user.id!!)
            .update(mapOf( "posts" to user.posts))
    }



    fun searchByName(query: String): LiveData<ArrayList<User>>  {
        var mutableLiveData = MutableLiveData<ArrayList<User>> ()
        val db = Firebase.firestore
        db.collection("users").orderBy("name").startAt(query).endAt(query + "\uf8ff")
            .get()
            .addOnSuccessListener {

                var relations = ArrayList<User>()



                for (item in it.documents) {

                    relations.add(item.toObject(User::class.java)!!)
                }
                mutableLiveData.value = relations

            }
        return mutableLiveData
    }




    private  fun getAccepted(id: String, user: User): MutableList<Friend>? {

        var i = 0
        if (i in 0..user.relations!!.size) {
            if (user.relations!!.get(i)?.id.equals(id)) {
                user.relations!!.get(i)?.id = user.id
                user.relations!!.get(i).state = "friend"
            }
        }

        return user.relations
    }

    private fun getSender(id : String, user: User): MutableList<Friend>? {

        var i=0
        if (i in 0..user.relations!!.size) {
            if (user.relations!!.get(i)?.id.equals(id)){
                user.relations!!.get(i)?.id = user.id
                user.relations!!.get(i).state = "+follower"
            }
        }

        return user.relations

    }




}