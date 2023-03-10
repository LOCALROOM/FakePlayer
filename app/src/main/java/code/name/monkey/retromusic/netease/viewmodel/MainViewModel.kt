package code.name.monkey.retromusic.netease.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import code.name.monkey.retromusic.netease.model.UserModel
import com.anrayus.apirequest.model.AccountInfo

class MainViewModel:ViewModel() {
    private val userModel = UserModel()
    private val _user = MutableLiveData<AccountInfo?>()
    val getUser:LiveData<AccountInfo?>
        get() = _user

     suspend fun getUserInfo(){
        userModel.getUserInfo{ user ->
            _user.value = user
        }
    }

}