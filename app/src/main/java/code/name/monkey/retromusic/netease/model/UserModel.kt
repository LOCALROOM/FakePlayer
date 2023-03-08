package code.name.monkey.retromusic.netease.model

import code.name.monkey.retromusic.netease.NeteasePreference.Companion.PREFERENCE_COOKIE
import code.name.monkey.retromusic.netease.NeteasePreference.Companion.loadStringInStorage
import code.name.monkey.retromusic.netease.NeteasePreference.Companion.saveInStorage
import com.anrayus.apirequest.LoginService
import com.anrayus.apirequest.store.CookieStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.Cookie

class UserModel:BaseModel {
    init {
        manager.setCookieEvent(object : CookieStore.CookieEvent{
            override fun saveCookieEvent(cookies: List<Cookie>, host: String) {
                //储存cookie
                val map = HashMap<String,List<Cookie>>()
                map[host] = cookies
                saveInStorage(PREFERENCE_COOKIE,Gson().toJson(map))
            }

            override fun loadCookieEvent(host: String, cookieMap: HashMap<String, List<Cookie>>) {
                //从储存中读取cookie并放置在CookieStore
                val type = object : TypeToken<Map<String, ArrayList<Cookie>>>() {}.type
                val json = loadStringInStorage(PREFERENCE_COOKIE)
                val map = Gson().fromJson<Map<String,List<Cookie>>>(json,type)
                val list = map[host]
                if (list != null) {
                    cookieMap[host] = list
                }
            }

        })
    }

    suspend fun getLoginQRCode(loginStatusEvent: LoginService.LoginStatusEvent,callback:(String?) -> Unit){
        val data = CoroutineScope(Dispatchers.IO).async {
            manager.getLoginQRCode(loginStatusEvent)?.qrimg
        }
        callback(data.await())
    }

    fun setCookieEvent(cookieEvent: CookieStore.CookieEvent){
        manager.setCookieEvent(cookieEvent)
    }

}