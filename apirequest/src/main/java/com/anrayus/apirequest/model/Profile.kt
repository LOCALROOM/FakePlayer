package com.anrayus.apirequest.model

data class Profile(val userId:String,val userType:Int,val nickName:String,val avatarImgId:Long,
                   val avatarUrl:String,val backgroundImgId:Long,val backgroundUrl:String,val signature:String,
                   val createTime:Long,val userName:String,val accountType:Int,val shortUserName:String,
                   val birthday:Long,val authority:Int,val gender:Int,val accountStatus:Int,val province:Int,
                   val city:Int,val authStatus:Int,val description:String,val detailDescription:String,val defaultAvatar:Boolean,
                   val expertTags:String,val experts:String,val djStatus:Int,val locationStatus:Int,val vipType:Int,
                   val followed:Boolean,val mutual:Boolean,val authenticated:Boolean,val lastLoginTime:Long,val lastLoginIP:String,
                   val remarkName:String,val viptypeVersion:Long,val authenticationTypes:Int,val avatarDetail:String,val anchor:Boolean)
