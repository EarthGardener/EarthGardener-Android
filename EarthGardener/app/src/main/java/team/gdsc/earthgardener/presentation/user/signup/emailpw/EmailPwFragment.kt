package team.gdsc.earthgardener.presentation.user.signup.emailpw

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.FragmentEmailPwBinding
import team.gdsc.earthgardener.presentation.base.BaseFragment
import team.gdsc.earthgardener.presentation.user.signup.nickname.NickNameFragment
import team.gdsc.earthgardener.presentation.user.signup.SignUpActivity
import java.util.regex.Pattern

class EmailPwFragment : BaseFragment<FragmentEmailPwBinding>(R.layout.fragment_email_pw) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkEmailPattern()
        checkEmailWatcher()
        getCodeEvent()
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
                // Get Code from email


                binding.tvCode.isVisible = true
                binding.etEmailCode.isVisible = true
                binding.tvCheckCode.isVisible = true
            }
        }
    }

    private fun checkCode(){
        binding.tvCheckCode.setOnClickListener {
            // check if code is right
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
        val signUpActivity = activity as SignUpActivity // 반복되는데 어떻게 하지

        if(binding.etSignupPw.text.isNotEmpty() ){ // 이메일 인증 코드 맞는지 여부 조건도 넣기
            signUpActivity.binding.btnNext.setBackgroundResource(R.drawable.btn_color_green)
            signUpActivity.binding.btnNext.isEnabled = true
        }else{
            signUpActivity.binding.btnNext.setBackgroundResource(R.drawable.btn_color_light_gray)
            signUpActivity.binding.btnNext.isEnabled = false
        }
    }

    private fun btnNextEvent(){
        val signUpActivity = activity as SignUpActivity
        signUpActivity.binding.btnNext.setOnClickListener {
            signUpActivity.binding.btnNext.text = getString(R.string.finish)
            signUpActivity.binding.btnNext.setBackgroundResource(R.drawable.btn_color_light_gray)
            signUpActivity.binding.btnNext.isEnabled = false
            signUpActivity.nextSignUpFragment(NickNameFragment())
        }
    }



}