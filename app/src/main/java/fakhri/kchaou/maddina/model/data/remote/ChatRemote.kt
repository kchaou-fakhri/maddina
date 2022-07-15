package fakhri.kchaou.maddina.model.data.remote

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import fakhri.kchaou.maddina.model.entity.Chat
import fakhri.kchaou.maddina.model.entity.Message
import fakhri.kchaou.maddina.utils.MessageResult

class ChatRemote {

    val db_chat = Firebase.firestore.collection("chats")
    val db_message = Firebase.firestore.collection("messages")



    fun sendMessage(){

    }

    fun createChat(chat: Chat): LiveData<MessageResult> {
        var mutableLiveData =MutableLiveData<MessageResult>()

       val result=    db_chat.document()
           result.set(chat)
        .addOnSuccessListener {

            mutableLiveData.value = MessageResult(true, result.id)
        }

        return mutableLiveData
    }

    fun ifInConnect(chat: Chat): LiveData<MessageResult> {

        var mutableLiveData =MutableLiveData<MessageResult>()



        db_chat.whereArrayContainsAny("usersID", chat.usersID).get().addOnSuccessListener {

          it.documents.forEach{

              val id = it.id

              if (it.toObject(Chat::class.java)?.usersID?.contains(chat.usersID.get(0))!! &&
                  it.toObject(Chat::class.java)?.usersID?.contains(chat.usersID.get(1))!!){

                  Log.println(Log.ASSERT, "-----------",id )

                  mutableLiveData.value = MessageResult(true, id)
              }
              else{
                  mutableLiveData.value = MessageResult(false, "")

              }

          }
            if (it.size() == 0){
                mutableLiveData.value = MessageResult(false, "")

            }


        }


        return mutableLiveData

    }

    fun sendMessage(message: Message): LiveData<Message> {

        var mutableLiveData =MutableLiveData<Message>()

        val result=    db_message.document()
        result.set(message)
            .addOnSuccessListener {
                message.id = result.id
                mutableLiveData.value = message
            }

        return mutableLiveData
    }


    fun getMessages(chatID : String): LiveData<ArrayList<Message>> {

        var mutableLiveData = MutableLiveData<ArrayList<Message>>()

        var messages = arrayListOf<Message>()
        db_message.orderBy("time").whereEqualTo("chatID", chatID).addSnapshotListener { value, e ->
        messages.clear()

            value?.documents?.forEach {
                if (!messages.contains(it.toObject(Message::class.java))) {


                    var _mesg = it.toObject(Message::class.java)!!
                    _mesg.id = it.id
                    messages.add(_mesg)
                }

            }
            mutableLiveData.value = messages
        }


        return mutableLiveData
    }

    fun getChats(userID : String) : LiveData<ArrayList<Chat>>{
        var mutableLiveData =MutableLiveData<ArrayList<Chat>>()


    var array = arrayListOf<Chat>()
        db_chat.whereArrayContains("usersID",userID).get().addOnSuccessListener {

            it.documents.forEach{


             var _chat = it.toObject(Chat::class.java)
                _chat?.id = it.id!!
                array.add(_chat!!)


            }
            if (it.size() == 0){
                mutableLiveData.value = null

            }
            mutableLiveData.value = array


        }


        return mutableLiveData
    }



    fun getOnDataChanged(chatID : String): LiveData<Message> {

        var mutableLiveData = MutableLiveData<Message>()

        var messages = arrayListOf<Message>()
        db_message.whereEqualTo("chatID", chatID).get().addOnSuccessListener {
            it?.documentChanges?.forEach {
                mutableLiveData.value = it.document.toObject(Message::class.java)
            }

            }
        Log.println(Log.ASSERT, TAG,mutableLiveData.value.toString() )




        return mutableLiveData
    }







}