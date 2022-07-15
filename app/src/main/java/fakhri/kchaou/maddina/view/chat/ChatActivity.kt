package fakhri.kchaou.maddina.view.chat

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.ActivityChatBinding
import fakhri.kchaou.maddina.model.entity.Chat
import fakhri.kchaou.maddina.model.entity.Message
import fakhri.kchaou.maddina.utils.LoadingAlert
import fakhri.kchaou.maddina.viewmodel.ChatVM
import java.util.*

class ChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatBinding
    lateinit var chatVM: ChatVM
    lateinit var loadingAlert : LoadingAlert
    lateinit var chatID : String
    lateinit var adapter : MessageAdapter
    lateinit var userId :String
    var ifFirstTime = true

    var array = arrayListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root

        val window: Window = this.getWindow()
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white))
        setContentView(view)

        chatVM = ChatVM()

        loadingAlert = LoadingAlert(this)
        loadingAlert.startLoadingAlert()


        val friendId = intent.extras!!.getString("friendId")
        val friendName = intent.extras!!.getString("friendName")

        val sharedPreferences: SharedPreferences? = this.getSharedPreferences("user", Context.MODE_PRIVATE)
            userId = sharedPreferences?.getString("userId", "").toString()

        /**************************************************************************************************************************************
        *****************                     Check if chat exist                       *******************************************************
        ***************************************************************************************************************************************/
        ifChatExist(Chat(null, arrayListOf(friendId!!,userId)))






        adapter = MessageAdapter(this,  array)
        binding.messages.layoutManager = LinearLayoutManager(this)

        binding.messages.adapter = adapter




        /**************************************************************************************************************************************
         *****************                     send Message                             *******************************************************
         ***************************************************************************************************************************************/
        binding.send.setOnClickListener {

        if (binding.message.text.isNotEmpty()){


                    chatVM.sendMessage(Message(null,binding.message.text.toString(), Date(), userId, friendId, chatID)).observe(this, Observer {

                        binding.message.text.clear()
                    })

        }

        }




        /**************************************************************************************************************************************
         *****************                     Create New Chat                           *******************************************************
         ***************************************************************************************************************************************/
        binding.btnCreateChat.text = "التواصل مع $friendName "

        binding.btnCreateChat.setOnClickListener { createNewChat(Chat(null, arrayListOf(friendId!!,userId)))}

    }


    private fun ifChatExist(chat : Chat) : Boolean{

        var ifExist = false
        chatVM.ifInConnect(chat).observe(this, Observer {
            if (it.statu == true){
                binding.firstConnect.visibility = View.INVISIBLE
                binding.chatsLayout.visibility  = View.VISIBLE
                chatID = it.message
                Log.println(Log.ASSERT, TAG, it.message)
                ifExist = true
                getAndDispalyData()



            }
            else{
                binding.firstConnect.visibility = View.VISIBLE
                binding.chatsLayout.visibility  = View.INVISIBLE


            }


            loadingAlert.dismissDialog()

        })
        return ifExist
    }

    private fun createNewChat(chat: Chat){
        chatVM.createChat(chat).observe(this, Observer {

            binding.firstConnect.visibility = View.INVISIBLE
            binding.chatsLayout.visibility  = View.VISIBLE
            chatID = it.message
            loadingAlert.dismissDialog()
            chatID = it.message

        })
    }

    private fun getAndDispalyData(){
        chatVM.getMessages(chatID).observe(this, Observer {


                it.forEach {
                    if(!array.contains(it)){
                        array.add(it)
                    }
                }



                adapter.notifyDataSetChanged()




        })


    }

    private fun updateData(){
        chatVM.getOnDataChanged(chatID).observe(this, Observer {
            array.add(it)
                        adapter.notifyDataSetChanged()

            Log.println(Log.ASSERT, "-----hhhh------",it.toString() )



        })
    }


}