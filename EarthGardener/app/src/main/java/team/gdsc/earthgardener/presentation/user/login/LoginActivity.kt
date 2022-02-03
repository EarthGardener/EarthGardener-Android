package team.gdsc.earthgardener.presentation.user.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivityLoginBinding
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.user.signup.SignUpActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnLoginEvent()
        btnSignUpEvent()
    }

    private fun btnLoginEvent(){
        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val pw = binding.etLoginPw.text.toString()
            // Post Login
        }
    }

    private fun btnSignUpEvent(){
        binding.tvLoginSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}