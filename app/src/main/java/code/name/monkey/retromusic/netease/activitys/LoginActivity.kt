package code.name.monkey.retromusic.netease.activitys

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import code.name.monkey.retromusic.R
import code.name.monkey.retromusic.activities.MainActivity
import code.name.monkey.retromusic.databinding.ActivityLoginBinding
import code.name.monkey.retromusic.netease.viewmodel.LoginViewModel
import com.anrayus.apirequest.LoginService
import com.anrayus.apirequest.store.CookieStore
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Cookie


class LoginActivity : AppCompatActivity() {
    lateinit var viewModel: LoginViewModel
    val jod = Job()
    val scope = CoroutineScope(jod+ Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        scope.launch(Dispatchers.Main){
            viewModel.onQRCodeRequest(object : LoginService.LoginStatusEvent(){
                override fun loginStatusChangeEvent(status: Int) {
                    when(status){
                        COMPLETE -> {
                            scope.launch(Dispatchers.Main) {
                                viewModel.getUserInfo()
                                viewModel.getUser.observe(this@LoginActivity){ user ->
                                    if (user!=null){
                                        MainActivity.callBackToMainActivity(this@LoginActivity,user)
                                    }
                                }
                            }
                        }
                    }
                }

            })
        }

        viewModel.getQRCodeResult.observe(this) { code ->
            val bitmap =  base64ToBitmap(code)
            val qrimg = findViewById<ImageView>(R.id.qrimg)
            qrimg.setImageBitmap(bitmap)
        }

    }

    fun base64ToBitmap(base64:String): Bitmap? {
        val base64Encoded = base64.split(",")[1]
        val imageData = Base64.decode(base64Encoded, android.util.Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)

    }
}