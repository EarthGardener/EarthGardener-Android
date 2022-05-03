package team.gdsc.earthgardener.presentation.user.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivitySignupEmailBinding
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.main.MainActivity
import java.util.regex.Pattern

class SignupEmailActivity : BaseActivity<ActivitySignupEmailBinding>(R.layout.activity_signup_email) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnSignUpEvent()
    }

    private fun btnSignUpEvent(){
        binding.btnFinish.setOnClickListener {
            if(checkEmailPattern()){


            }else{
                Toast.makeText(this, "이메일 형식에 맞추어 작성해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkEmailPattern(): Boolean {
        val email = binding.etSignUpEmail.text.toString().trim()
        val emailValidation =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val p = Pattern.matches(emailValidation, email)

        return if(p){
            binding.etSignUpEmail.setTextColor(ContextCompat.getColor(this, R.color.text_black))
            true
        }else{
            binding.etSignUpEmail.setTextColor(ContextCompat.getColor(this, R.color.accent_pink))
            false
        }
    }

    private fun kakaoSignup(){
        // kakao 로그인 성공한 경우
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}