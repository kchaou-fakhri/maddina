package fakhri.kchaou.maddina.model.entity

import java.time.LocalDateTime
import java.util.*


data class Post(var user: User? = null, var text: String? ="", var media_url: String? = null, var created_at: String? = null, var id : String?="") {


}