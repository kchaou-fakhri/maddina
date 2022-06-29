package fakhri.kchaou.maddina.model.entity

import java.lang.reflect.Constructor


data class User (var id: String? = "",var name: String? = "",
                 var email :String? = "", var password : String ="password",
                 var sex:String? = "",  var job :String? = "",
                 var adress : String? = "" ,var bio : String? = "",
                 var relations : ArrayList<Friend>? = arrayListOf(),
                 var posts : ArrayList<String>? = arrayListOf()
                )
{


}