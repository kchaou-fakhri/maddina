package fakhri.kchaou.maddina.model.data.repository

import androidx.lifecycle.LiveData
import fakhri.kchaou.maddina.model.data.remote.ChatRemote
import fakhri.kchaou.maddina.model.entity.Chat
import fakhri.kchaou.maddina.model.entity.Message
import fakhri.kchaou.maddina.utils.MessageResult

class ChatRepository {

    var chatRemote : ChatRemote

    init {
        chatRemote = ChatRemote()
    }


    fun createChat(chat: Chat): LiveData<MessageResult> {
        return chatRemote.createChat(chat)
    }

    fun ifInConnect(chat: Chat): LiveData<MessageResult> {
        return chatRemote.ifInConnect(chat)
    }

    fun sendMessage(message: Message): LiveData<Message> {
        return chatRemote.sendMessage(message)
    }

    fun getMessages(chatID: String): LiveData<ArrayList<Message>> {
        return chatRemote.getMessages(chatID)
    }

    fun getChats(id: String): LiveData<ArrayList<Chat>> {
        return chatRemote.getChats(id)
    }

    fun getOnDataChanged(id: String): LiveData<Message> {
        return chatRemote.getOnDataChanged(id)
    }
}