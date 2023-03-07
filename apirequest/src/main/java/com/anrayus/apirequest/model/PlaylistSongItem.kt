package com.anrayus.apirequest.model

import com.google.gson.annotations.SerializedName

class PlaylistSongItem(val name:String, @SerializedName("0")val artis: List<Artis>, val id:String, val al: Al) {

}
class Al(picUrl:String)