package com.anrayus.apirequest

import com.anrayus.apirequest.model.*
import com.anrayus.apirequest.store.CookieStore
import com.google.gson.Gson
import kotlinx.coroutines.*

/**
 * @param loginStatus 使用者定义的状态事件
 *
 */
open class LoginService: BaseService(){
    private var key:String? = null
    /**
     * 获取登录的二维码
     *
     */
    fun getQRCode(loginStatusEvent : LoginStatusEvent): QRCodeInfo? {
        key = getKey()
        return if (key!=null) {
            val param = Pair("qrimg","true")
            val qrcodeStation = request("/login/qr/create",key,param)
            val qrCode = Gson().fromJson(qrcodeStation
                ,
                QRCode::class.java
            )
            return if (qrCode.code == 200) {
                loginStatusEvent.checkLoginStatus(key!!)//开启状态检查
                qrCode.data

            }else{
                null
            }
        }else{
            null
        }

    }

    private fun getKey():String?{
        val res = request("/login/qr/key",null)
        val key = Gson().fromJson(res, Key::class.java)
        return if (key.code==200){
            key.data.unikey
        }else{
            null
        }

    }

    /**
     * 使用抽象类，为用户自定义状态事件留下接口
     * 必须重写[LoginStatusEvent.loginStatusChangeEvent]
     */
    abstract class LoginStatusEvent: LoginService(){
        companion object{
            const val COMPLETE = 803
            const val WAIT_LOGIN = 801
            const val LOGGING = 802
        }
        abstract fun loginStatusChangeEvent(status:Int)//使用者需重写该方法以定义状态事件
        fun checkLoginStatus(key:String){
            CoroutineScope(Dispatchers.IO).launch {
                var status = WAIT_LOGIN
                while (true) {
                    val codeStatus = Gson().fromJson(
                        request("/login/qr/check", key),
                        QRCodeStatusInfo::class.java
                    )
                    status = codeStatus.code
                    loginStatusChangeEvent(status)
                    when (status) {
                        WAIT_LOGIN -> {
                            delay(1000)
                        }
                        LOGGING -> {
                            delay(500)
                        }
                    }
                    if (status == COMPLETE) {
                        //保存Cookie
                        CookieStore.save(RequestManager.getHost(),codeStatus.cookie)
                        return@launch
                    }
                }
            }
        }
    }

}


