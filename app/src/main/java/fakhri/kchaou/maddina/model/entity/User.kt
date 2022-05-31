package fakhri.kchaou.maddina.model.entity

data class User (var id: String?,var name: String,
                 var email :String, var password : String ="password",var sex:String? = null)