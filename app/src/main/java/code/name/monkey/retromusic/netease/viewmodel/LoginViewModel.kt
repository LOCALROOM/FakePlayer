package code.name.monkey.retromusic.netease.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import code.name.monkey.retromusic.netease.model.UserModel
import com.anrayus.apirequest.LoginService
import com.anrayus.apirequest.store.CookieStore

class LoginViewModel :ViewModel(){
    private val userModel = UserModel()
    private val _QRCodeResult = MutableLiveData<String>()
    val getQRCodeResult:LiveData<String>
        get() =_QRCodeResult

    suspend fun onQRCodeRequest(loginStatusEvent: LoginService.LoginStatusEvent){
        userModel.getLoginQRCode(loginStatusEvent) { url ->
            _QRCodeResult.value = url
        }
    }

    fun setCookieEvent(cookieEvent: CookieStore.CookieEvent){
        userModel.setCookieEvent(cookieEvent)
    }



}