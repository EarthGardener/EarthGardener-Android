package team.gdsc.earthgardener.presentation.user.login

import android.content.Intent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivityLoginBinding
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.X_ACCESS_TOKEN
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.editor

import team.gdsc.earthgardener.presentation.main.MainActivity
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.user.signup.SignUpActivity
import team.gdsc.earthgardener.presentation.user.signup.login.LoginRequest
import team.gdsc.earthgardener.presentation.user.signup.login.LoginResponse
import team.gdsc.earthgardener.presentation.user.signup.login.LoginRetrofitInterface
import team.gdsc.earthgardener.presentation.user.signup.retrofit.SignUpRetrofitClient
import java.util.regex.Pattern

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        btnLoginEvent()
        navigateToSignUp()
    }

    private fun btnLoginEvent(){
        binding.btnLogin.setOnClickListener {
            if(checkEmailPattern()){
                val email = binding.etLoginEmail.text.toString().trim()
                val pw = binding.etLoginPw.text.toString().trim()
                // Post Login
                val loginRequest = LoginRequest(email, pw)
                postLoginData(loginRequest)
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

    private fun postLoginData(data: LoginRequest){
        val loginInterface = SignUpRetrofitClient.sRetrofit.create(LoginRetrofitInterface::class.java)

        loginInterface.postLogin(data).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    editor.putString(X_ACCESS_TOKEN, response.body()!!.token)
                    editor.commit()
                    navigateToMain()
                }else{
                    Toast.makeText(applicationContext, "잘못된 정보입니다", Toast.LENGTH_SHORT).show()
                    Log.d("fail", "error code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("onFailure", t.message ?: "통신오류")
            }

        })
    }

    private fun checkEmailPattern(): Boolean {
        val email = binding.etLoginEmail.text.toString().trim()
        val emailValidation =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        return Pattern.matches(emailValidation, email)
    }
}