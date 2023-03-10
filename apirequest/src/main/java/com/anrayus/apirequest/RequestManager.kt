package com.anrayus.apirequest

import com.anrayus.apirequest.store.CookieStore
import com.anrayus.apirequest.model.PlaylistSongItem
import com.anrayus.apirequest.model.QRCodeInfo

class RequestManager {
    companion object{
        private var HOST = ""
        private var PORT = ""

        fun build(host:String,port:String):RequestManager{
            HOST = host
            PORT = port
            return RequestManager()
        }

        fun getBaseUrl() = "$HOST:$PORT"
        fun getHost() = HOST
        fun getPort() = PORT

    }

    private val loginService = LoginService()
    /*************LoginService*************/
    fun getLoginQRCode(loginStatusEvent: LoginService.LoginStatusEvent): QRCodeInfo? {
        return loginService.getQRCode(loginStatusEvent)
    }
    fun setCookieEvent(cookieEvent: CookieStore.CookieEvent){
        CookieStore.setCookieEvent(cookieEvent)
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