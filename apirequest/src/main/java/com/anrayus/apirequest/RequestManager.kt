package com.anrayus.apirequest

import com.anrayus.apirequest.store.CookieStore
import com.anrayus.apirequest.model.PlaylistSongItem
import com.anrayus.apirequest.model.QRCodeInfo

class RequestManager {
    companion object{
        private var BASE_URL = ""

        fun build(url:String):RequestManager{
            BASE_URL = url
            return RequestManager()
        }

        fun getBaseUrl() = BASE_URL

    }

    private val loginService = LoginService()
    /*************LoginService*************/
    fun getLoginQRCode(loginStatusEvent: LoginService.LoginStatusEvent): QRCodeInfo? {
        return loginService.getQRCode(loginStatusEvent)
    }
    fun setCookieEvent(cookieEvent: CookieStore.CookieEvent){
        loginService.cookieStore.setCookieEvent(cookieEvent)
    }
    /*************PlaylistService*************/
    private val playlistService = PlaylistService()
    fun getUserPlaylist(uid:String) = playlistService.getUserPlaylist(uid)
    fun getPlaylistSongInfo(id:String): List<PlaylistSongItem> = playlistService.getAllSongInPlaylist(id)
    /*************SongService*************/
    private val songService = SongService()
    fun getSongUrl(ids:List<String>,level:String) = songService.getSongUrl(ids,level)
    /*************UserService*************/
    private val userService = UserService()
    fun getUserInfo() = userService.getAccountInfo()
}