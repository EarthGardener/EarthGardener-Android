package team.gdsc.earthgardener.presentation.user.login

import android.content.Intent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
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
    }

    private fun btnLoginEvent(){
        binding.btnLogin.setOnClickListener {
            if(checkEmailPattern()){
                val email = binding.etLoginEmail.text.toString().trim()
                val pw = binding.etLoginPw.text.toString().trim()

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
}