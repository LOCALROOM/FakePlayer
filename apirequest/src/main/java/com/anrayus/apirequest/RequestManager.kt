package com.anrayus.apirequest

import com.anrayus.apirequest.store.CookieStore
import com.anrayus.apirequest.model.PlaylistSongItem
import com.anrayus.apirequest.model.QRCodeInfo

class RequestManager(url:String) {
    companion object{
        lateinit var baseUrl:String
    }
    init {
        baseUrl = url
    }

    /*************LoginService*************/
    private lateinit var statusChangeEvent:LoginService.LoginStatus
    private val loginService = LoginService(statusChangeEvent)
    fun setLoginStatusChangeEvent(statusChangeEvent:LoginService.LoginStatus){
        this.statusChangeEvent = statusChangeEvent
    }

    fun getLoginQRCode(): QRCodeInfo? = loginService.getQRCode()

    fun setCookieEvent(cookieEvent: CookieStore.CookieEvent){
        loginService.cookieStore.setCookieEvent(cookieEvent)
    }

    /*************PlaylistService*************/
    val playlistService = PlaylistService()
    fun getUserPlaylist(uid:String) = playlistService.getUserPlaylist(uid)

    fun getPlaylistSongInfo(id:String): List<PlaylistSongItem> = playlistService.getAllSongInPlaylist(id)

    /*************SongService*************/
    private val songService = SongService()
    fun getSongUrl(ids:List<String>,level:String) = songService.getSongUrl(ids,level)

    /*************UserService*************/
    private val userService = UserService()
    fun getUserInfo() = userService.getAccountInfo()





}