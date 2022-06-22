package fakhri.kchaou.maddina.model.entity

/*************** if state equals friend, user can be show all posts and info of friend *************************************
 *************** if state equals follower, user can be show only posts create for followers and info of friend, ("-" for sender, "+" for getter ) ************
 *************** if state equals user, user can be show only name and job title of friend  ************/
data class Friend(var id: String? = null, var state : String? ="user")
