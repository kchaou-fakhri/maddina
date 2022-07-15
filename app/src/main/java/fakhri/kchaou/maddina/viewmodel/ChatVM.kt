package fakhri.kchaou.maddina.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fakhri.kchaou.maddina.model.data.repository.ChatRepository
import fakhri.kchaou.maddina.model.entity.Chat
import fakhri.kchaou.maddina.model.entity.Message
import fakhri.kchaou.maddina.utils.MessageResult

class ChatVM : ViewModel() {
   var chatRepository : ChatRepository

    init {
        chatRepository = ChatRepository()
    }

    fun createChat(chat : Chat) : LiveData<MessageResult>{

        return chatRepository.createChat(chat)

    }

    fun ifInConnect(chat: Chat): LiveData<MessageResult> {
        return chatRepository.ifInConnect(chat)
    }

    fun sendMessage(message: Message) : LiveData<Message>{
        return chatRepository.sendMessage(message)
    }

    fun getMessages(chatID: String): LiveData<ArrayList<Message>> {
        return chatRepository.getMessages(chatID)
    }

    fun getChats(id: String): LiveData<ArrayList<Chat>>  {
        return chatRepository.getChats(id)
    }

    fun getOnDataChanged(id: String): LiveData<Message> {
        return chatRepository.getOnDataChanged(id)
    }


}