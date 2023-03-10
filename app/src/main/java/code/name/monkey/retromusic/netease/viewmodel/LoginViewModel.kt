package code.name.monkey.retromusic.netease.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import code.name.monkey.retromusic.netease.model.UserModel
import com.anranyus.apirequest.LoginService
import com.anranyus.apirequest.model.AccountInfo

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

    private val _user = MutableLiveData<AccountInfo?>()
    val getUser:LiveData<AccountInfo?>
        get() = _user

    suspend fun getUserInfo(){
        userModel.getUserInfo{ user ->
            _user.value = user
        }
    }


}