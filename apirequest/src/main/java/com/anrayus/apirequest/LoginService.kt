package com.anrayus.apirequest

import com.anrayus.apirequest.model.Key
import com.anrayus.apirequest.model.QRCode
import com.anrayus.apirequest.model.QRCodeInfo
import com.anrayus.apirequest.model.QRCodeStatusInfo
import com.google.gson.Gson

/**
 * @param loginStatus 使用者定义的状态事件
 *
 */
class LoginService(private val loginStatus: LoginStatus): BaseService(){

    private var key:String? = null
    override val BASE_URL: String = "${super.BASE_URL}/login"

    /**
     * 获取登录的二维码
     *
     */
    fun getQRCode(): QRCodeInfo? {
        key = getKey()
        return if (key!=null) {
            val param = Pair("qrimg","true")
            val qrcodeStation = request("/qr/create",key,param)
            val qrCode = Gson().fromJson(qrcodeStation
                ,
                QRCode::class.java
            )
            return if (qrCode.code == 200) {
                loginStatus.checkLoginStatus(key!!)//开启状态检查
                return qrCode.data

            }else{
                null
            }
        }else{
            null
        }

    }

    private fun getKey():String?{
        val res = request("/qr/key",null)
        val key = Gson().fromJson(res, Key::class.java)
        return if (key.code==200){
            key.data.unikey
        }else{
            null
        }

    }

    /**
     * 使用抽象类，为用户自定义状态事件留下接口
     * 必须重写[LoginStatus.loginStatusChangeEvent]
     */
    abstract class LoginStatus: BaseService(){
        override val BASE_URL: String = "${super.BASE_URL}/login"
        abstract fun loginStatusChangeEvent(status:Int)//使用者需重写该方法以定义状态事件
        fun checkLoginStatus(key:String){
            var status = 801
            while (true){
                val codeStatus = Gson().fromJson(request("/qr/check",key), QRCodeStatusInfo::class.java)
                status = codeStatus.code
                loginStatusChangeEvent(status)
                if (status==803){
                    return//登录完成 退出
                }
            }

        }
    }

}


