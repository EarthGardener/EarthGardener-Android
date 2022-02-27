package team.gdsc.earthgardener.presentation.user.signup.emailpw

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.FragmentEmailPwBinding
import team.gdsc.earthgardener.presentation.base.BaseFragment
import team.gdsc.earthgardener.presentation.user.signup.nickname.NickNameFragment
import team.gdsc.earthgardener.presentation.user.signup.SignUpActivity
import team.gdsc.earthgardener.presentation.user.signup.emailpw.viewModel.CheckEmailViewModel
import team.gdsc.earthgardener.presentation.user.signup.viewModel.SignUpViewModel
import java.util.regex.Pattern
import kotlin.concurrent.thread

class EmailPwFragment : BaseFragment<FragmentEmailPwBinding>(R.layout.fragment_email_pw) {

    private val checkEmailViewModel: SignUpViewModel by sharedViewModel()

    private var emailCode: String?= null
    private var checkEmailCode = false

    private var totalTime = 0
    private var started = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkEmailPattern()
        checkEmailWatcher()
        getCodeEvent()
        observeCheckEmailIfSignedUp()
        observeCheckEmailCode()
        checkCode()
        etPasswordWatcher()
        btnNextEvent()
    }

    private fun checkEmailPattern(): Boolean{
        val email = binding.etSignUpEmail.text.toString().trim()
        val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val p = Pattern.matches(emailValidation, email)
        return if(p){
            binding.etSignUpEmail.setTextColor(ContextCompat.getColor(context!!, R.color.text_black))
            true
        }else{
            binding.etSignUpEmail.setTextColor(ContextCompat.getColor(context!!, R.color.accent_pink))
            false
        }
    }

    private fun checkEmailWatcher(){
        binding.etSignUpEmail.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                checkEmailPattern()
            }

        })
    }

    private fun getCodeEvent(){
        binding.tvGetCode.setOnClickListener {
            if(checkEmailPattern()){
                // 통신 Get Code from email
                checkEmailViewModel.email = binding.etSignUpEmail.text.toString().trim()
                checkEmailViewModel.getEmail()
            }
        }
    }

    private fun observeCheckEmailIfSignedUp(){
        checkEmailViewModel.emailStatus.observe(viewLifecycleOwner){
            if(it == 409){
                Toast.makeText(context, "이미 가입된 이메일입니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeCheckEmailCode(){
        checkEmailViewModel.currentCode.observe(viewLifecycleOwner){
            Toast.makeText(context, "해당 이메일로 인증 코드를 보냈습니다", Toast.LENGTH_SHORT).show()

            emailCode = it.toString()
            Log.d("emailCode", emailCode!!)

            binding.tvCode.isVisible = true
            binding.linearEmailCode.isVisible = true

            totalTime = 60 * 3 // 3분
            startEmailCodeTimer()
        }
    }

    private fun startEmailCodeTimer(){
        val handler = object: Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                val minute = String.format("%02d", totalTime/60)
                val second = String.format("%02d", totalTime%60)

                binding.tvEmailTimer.text = "$minute:$second"

                if(totalTime == 0){
                    Toast.makeText(context, "이메일 코드를 다시 발급받으세요", Toast.LENGTH_SHORT).show()
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

    private fun checkCode(){
        binding.tvCheckCode.setOnClickListener {
            // check if code is right
            if(emailCode.equals(binding.etEmailCode.text.toString().trim())){
                checkEmailCode = true
                Toast.makeText(context, "인증에 성공했습니다", Toast.LENGTH_SHORT).show()

                started = false
                binding.tvEmailTimer.text = "00:00"

                binding.tvGetCode.isEnabled = false
                binding.tvCheckCode.isEnabled = false
                binding.etSignUpEmail.isFocusableInTouchMode = false // 수정 불가
            }else{
                Toast.makeText(context, "인증코드를 잘못 입력하셨습니다", Toast.LENGTH_SHORT).show()
                binding.etEmailCode.text.clear()
            }
        }
    }

    private fun etPasswordWatcher(){
        binding.etSignupPw.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnNextActive()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun btnNextActive(){
        val signUpActivity = activity as SignUpActivity

        if(binding.etSignupPw.text.isNotEmpty() && checkEmailCode){ // 이메일 인증 코드 맞는지 여부 조건도 넣기
            signUpActivity.binding.btnNext.setBackgroundResource(R.drawable.rectangle_primary_green_radius_30)
            signUpActivity.binding.btnNext.isEnabled = true
        }else{
            signUpActivity.binding.btnNext.setBackgroundResource(R.drawable.rectangle_light_gray_radius_30)
            signUpActivity.binding.btnNext.isEnabled = false
        }
    }

    private fun btnNextEvent(){
        started = false

        val signUpActivity = activity as SignUpActivity
        signUpActivity.binding.btnNext.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("email", binding.etSignUpEmail.text.toString().trim())
            bundle.putString("pw", binding.etSignupPw.text.toString().trim())

            signUpActivity.binding.btnNext.text = getString(R.string.finish)
            signUpActivity.binding.btnNext.setBackgroundResource(R.drawable.rectangle_light_gray_radius_30)
            signUpActivity.binding.btnNext.isEnabled = false
            signUpActivity.nextSignUpFragment(NickNameFragment(), bundle)
        }
    }

}