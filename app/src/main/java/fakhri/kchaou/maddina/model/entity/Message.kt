package fakhri.kchaou.maddina.model.entity

import java.util.*

data class Message(
    var id: String? = null,
    val text: String? = null,
    val time: Date= Date(),
    val senderID: String? = null,
    val receiverID: String? = null,
    val chatID: String? = null
                 )

