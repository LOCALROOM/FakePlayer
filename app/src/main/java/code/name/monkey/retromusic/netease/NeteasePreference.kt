package code.name.monkey.retromusic.netease

import android.content.Context
import code.name.monkey.retromusic.App


class NeteasePreference {
    companion object{
        private lateinit var BASE_URL:String
        private const val NAME = "netease_cloud_music_preference"
        const val PREFERENCE_URL = "preference_url"
        const val PREFERENCE_COOKIE = "preference_cookie"

        fun setBaseUrl(host:String,port:String){
            BASE_URL = "$host:$port"
            saveInStorage(PREFERENCE_URL, BASE_URL)
        }

        fun loadStringInStorage(prefer: String):String?=
            App.getContext().getSharedPreferences(NAME,Context.MODE_PRIVATE).getString(prefer,null)

        fun saveInStorage(prefer:String,value:String){
            App.getContext().getSharedPreferences(NAME,Context.MODE_PRIVATE).edit().putString(prefer,value).apply()
        }
    }
}

