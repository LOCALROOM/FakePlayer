package code.name.monkey.retromusic.netease.model

import code.name.monkey.retromusic.netease.NeteasePreference.PREFERENCE_COOKIE
import code.name.monkey.retromusic.netease.NeteasePreference.loadStringInStorage
import code.name.monkey.retromusic.netease.NeteasePreference.saveInStorage
import com.anrayus.apirequest.LoginService
import com.anrayus.apirequest.model.AccountInfo
import com.anrayus.apirequest.store.CookieStore
import kotlinx.coroutines.*

class UserModel:BaseModel {
    val job: CompletableJob = Job()
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO+job)

    init {
        manager.setCookieEvent(object : CookieStore.CookieEvent{
            override fun saveCookieEvent(cookies: String, host: String) {
                //储存cookie
                saveInStorage(PREFERENCE_COOKIE,cookies)
            }

            override fun loadCookieEvent(host: String, cookieMap: HashMap<String, String>) {
                //从储存中读取cookie并放置在CookieStore
                val cookie = loadStringInStorage(PREFERENCE_COOKIE)
                if (cookie!=null){
                    cookieMap[host] = cookie
                }
            }

        })
    }

    suspend fun getLoginQRCode(loginStatusEvent: LoginService.LoginStatusEvent,callback:(String?) -> Unit){
        val data = scope.async {
            manager.getLoginQRCode(loginStatusEvent)?.qrimg
        }
        callback(data.await())
    }

    suspend fun getUserInfo(callback:(AccountInfo?) -> Unit) {
        val data = scope.async {
            manager.getUserInfo()
        }
        callback(data.await())
    }

}