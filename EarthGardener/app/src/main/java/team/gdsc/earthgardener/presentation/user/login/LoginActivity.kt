package team.gdsc.earthgardener.presentation.user.login

import android.content.Intent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApi
import com.kakao.sdk.user.UserApiClient
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivityLoginBinding
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.editor


import team.gdsc.earthgardener.presentation.main.MainActivity
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.user.login.viewModel.SignInViewModel
import team.gdsc.earthgardener.presentation.user.signup.SignUpActivity
import java.util.regex.Pattern

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val signInViewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        btnLoginEvent()
        navigateToSignUp()
        observeSignIn()
        btnKakaoLoginEvent()

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if(error != null){

            }else{
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        UserApiClient.instance.accessTokenInfo{tokenInfo, error ->
            if(error != null){
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            }else if(tokenInfo != null){
                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        binding.btnKakaoLogin.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    private fun btnLoginEvent(){
        binding.btnLogin.setOnClickListener {
            if(checkEmailPattern()){
                val email = binding.etLoginEmail.text.toString().trim()
                val pw = binding.etLoginPw.text.toString().trim()

                showLoadingDialog(this)
                // Post Login
                signInViewModel.email = email
                signInViewModel.pw = pw
                signInViewModel.postSignIn()
            }else{
                Toast.makeText(this, "이메일 형식에 맞추어 작성해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToSignUp(){
        binding.tvLoginSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun navigateToMain(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun observeSignIn(){
        signInViewModel.isSignIn.observe(this, Observer {
            dismissLoadingDialog()
            if(it){
                navigateToMain()
            }else{
                Toast.makeText(applicationContext, "이메일 또는 비밀번호가 잘못된 정보입니다", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun checkEmailPattern(): Boolean {
        val email = binding.etLoginEmail.text.toString().trim()
        val emailValidation =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        return Pattern.matches(emailValidation, email)
    }

    private fun btnKakaoLoginEvent(){



    }
}