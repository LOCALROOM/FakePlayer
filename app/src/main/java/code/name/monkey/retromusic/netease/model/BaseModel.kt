package code.name.monkey.retromusic.netease.model

import com.anrayus.apirequest.RequestManager
import kotlinx.coroutines.Job

interface BaseModel {
    val manager: RequestManager
//        get() = RequestManager(if (getStringInStorage(PREFERENCE_URL)!=null){
//            getStringInStorage(PREFERENCE_URL)
//        }else{
//            //TODO 无host逻辑
//            throw NullPointerException()
//        }!!)
        get() {
            return RequestManager.build("http://192.168.1.165:3000")
        }
}