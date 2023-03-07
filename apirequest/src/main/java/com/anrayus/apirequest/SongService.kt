package com.anrayus.apirequest

import com.anrayus.apirequest.model.Song
import java.lang.StringBuilder

class SongService: BaseService() {
    fun getSongUrl(songIds:List<String>,level:String):List<Song>{
        val ids = StringBuilder()
        songIds.forEach {
            ids.append("$it,")
        }
        val _id = Pair ("id",ids.substring(0,ids.length-1))
        val _level =Pair ("level",level)
        val res = request("/song/url/v1",null,_id,_level)
        class SongInfo(val data:List<Song>)
        return gson.fromJson(res,SongInfo::class.java).data
    }

}