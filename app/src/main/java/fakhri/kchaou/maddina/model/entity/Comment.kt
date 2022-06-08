package fakhri.kchaou.maddina.model.entity

data class Comment(val owner: User? = null, val created_at: String ="", val text :String? ="")
