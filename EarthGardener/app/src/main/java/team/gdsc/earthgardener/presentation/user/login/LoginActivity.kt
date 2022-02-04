package team.gdsc.earthgardener.presentation.user.login

import android.content.Intent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.os.Bundle
import android.util.Log
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

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        btnLoginEvent()
        navigateToSignUp()
    }

    private fun btnLoginEvent(){
        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString().trim()
            val pw = binding.etLoginPw.text.toString().trim()
            // Post Login
            val loginRequest = LoginRequest(email, pw)
            postLoginData(loginRequest)
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
                    Log.d("fail", "error code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("onFailure", t.message ?: "통신오류")
            }

        })
    }
}