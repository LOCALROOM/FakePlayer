package com.anrayus.apirequest

import com.anrayus.apirequest.model.Playlist
import com.anrayus.apirequest.model.PlaylistItem
import com.anrayus.apirequest.model.PlaylistSongItem


class PlaylistService : BaseService(){

    /**
     * 获取用户的歌单
     * @param uid 用户id 通过[UserService.getAccountInfo]获取
     *
     */
    fun getUserPlaylist(uid:String):List<PlaylistItem>{
        val params = Pair("uid",uid)
        return gson.fromJson(request("/user/playlist",null,params), Playlist::class.java).playlist
    }

    /**
     * 获取歌单内的歌曲信息
     * @param id 歌单ID
     */
    fun getAllSongInPlaylist(id: String): List<PlaylistSongItem> {
        class SongInfo(val songs:List<PlaylistSongItem>)//Temp class
        val params = Pair("id", id)
        val json = request("/playlist/track/all", null, params)
        return gson.fromJson(/* json = */ json, SongInfo::class.java).songs
    }



}