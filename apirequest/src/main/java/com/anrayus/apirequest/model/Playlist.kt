package com.anrayus.apirequest.model

class Playlist(val playlist: List<PlaylistItem>)
class Creator(val province:String,val followed:Boolean,val avatarUrl:String,
              val city:String,val userId:String,val nickname:String,val description:String,
              val detailDescription:String,val backgroundUrl:String,
              val vipType:Int)
class PlaylistItem(val subscribed:Boolean, val creator: Creator, val coverImgUrl:String, val createTime:Long, val userId: String,
                   val playCount:Int, val tags:ArrayList<String>, val name:String, val id:String)
class PlaylistSong(val songs:ArrayList<Song>)
