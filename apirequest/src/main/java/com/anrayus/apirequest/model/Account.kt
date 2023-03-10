package com.anrayus.apirequest.model

data class Account(val account: AccountInfo)
data class AccountInfo(val id:String,val username:String,val type:Int,val status:Int,
                       val whitelistAuthority:Int,val createTime:String,val tokenVersion:String,
                       val ban:Int,val baoyueVersion:Int,val donateVersion:Int,val vipType:Int,
                       val anonimousUser:Boolean,val paidFee:Boolean)
