package team.gdsc.earthgardener.presentation.user.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.content.ContextCompat
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivitySignupEmailBinding
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.main.MainActivity
import java.util.regex.Pattern
import kotlin.concurrent.thread

class SignupEmailActivity : BaseActivity<ActivitySignupEmailBinding>(R.layout.activity_signup_email) {

    private var emailCode: String? = null
    private var checkEmailCode = false

    private var totalTime = 0
    private var started = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkEmailWatcher()
        getEmailCodeEvent()
        observeGetEmailCode()
        checkEmailCode()
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

    private fun checkEmailWatcher(){
        binding.etSignUpEmail.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.etSignUpEmail.text.isNotEmpty()){
                    binding.btnFinish.setBackgroundResource(R.drawable.rectangle_primary_green_radius_30)
                    binding.btnFinish.isEnabled = true
                }else{
                    binding.btnFinish.setBackgroundResource(R.drawable.rectangle_light_gray_radius_30)
                    binding.btnFinish.isEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                checkEmailPattern()
            }

        })
    }

    private fun getEmailCodeEvent(){
        binding.tvGetCode.setOnClickListener{
            if(checkEmailPattern()){
                showLoadingDialog(this)

                // 이메일 보내고 코드 받기
            }
        }
    }

    private fun observeGetEmailCode(){
        // 코드 입력 제한 시간
        startEmailCodeTimer()
    }

    private fun startEmailCodeTimer(){
        val handler = object: Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                val minute = String.format("%02d", totalTime/60)
                val seconde = String.format("02d", totalTime%60)

                binding.tvEmailTimer.text = "$minute:$seconde"

                if(totalTime == 0){
                    Toast.makeText(this@SignupEmailActivity, "이메일 코드를 다시 발급받으세요", Toast.LENGTH_SHORT).show()
                }
            }
        }

        started = true
        thread(started){
            while (started){
                Thread.sleep(1000)
                totalTime -= 1
                handler.sendEmptyMessage(0)

                if(totalTime == 0){
                    totalTime = 0
                    break
                }
            }
        }
    }

    private fun checkEmailCode(){
        // 이메일 코드 확인
        binding.tvCheckCode.setOnClickListener{
            if(emailCode.equals(binding.etEmailCode.text.toString().trim())){
                checkEmailCode = true
                Toast.makeText(this, "인증에 성공했습니다", Toast.LENGTH_SHORT).show()

                started = false
                binding.tvEmailTimer.text = "00:00"

                binding.tvGetCode.isEnabled = false
                binding.tvCheckCode.isEnabled = false
                binding.etSignUpEmail.isFocusableInTouchMode = false
            }else{
                Toast.makeText(this, "인증코드를 잘못 입력하셨습니다", Toast.LENGTH_SHORT).show()
                binding.etEmailCode.text.clear()
            }
        }
    }

    private fun kakaoSignup(){
        // kakao 로그인 성공한 경우
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}