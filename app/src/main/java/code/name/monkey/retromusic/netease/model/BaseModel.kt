package code.name.monkey.retromusic.netease.model

import code.name.monkey.retromusic.netease.NeteasePreference
import com.anranyus.apirequest.RequestManager
import com.anranyus.apirequest.store.CookieStore

interface BaseModel {
    val manager: RequestManager
//        get() = RequestManager(if (getStringInStorage(PREFERENCE_URL)!=null){
//            getStringInStorage(PREFERENCE_URL)
//        }else{
//
//            throw NullPointerException()
//        }!!)
        //TODO 测试地址
        get() {
            //Put cookie in CookieStore
            CookieStore.put("http://192.168.1.165",NeteasePreference.loadStringInStorage(NeteasePreference.PREFERENCE_COOKIE))
            return RequestManager.build("http://192.168.1.165","3000")
        }
}