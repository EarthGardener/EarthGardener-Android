package team.gdsc.earthgardener.presentation.user.signup.emailpw

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.FragmentEmailPwBinding
import team.gdsc.earthgardener.presentation.base.BaseFragment
import team.gdsc.earthgardener.presentation.user.signup.nickname.NickNameFragment
import team.gdsc.earthgardener.presentation.user.signup.SignUpActivity
import team.gdsc.earthgardener.presentation.user.signup.emailpw.viewModel.CheckEmailViewModel
import java.util.regex.Pattern

class EmailPwFragment : BaseFragment<FragmentEmailPwBinding>(R.layout.fragment_email_pw) {

    private val checkEmailViewModel: CheckEmailViewModel by viewModel()
    private var emailCode: String?= null
    private var checkEmailCode = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkEmailPattern()
        checkEmailWatcher()
        getCodeEvent()
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
                binding.tvCode.isVisible = true
                binding.linearEmailCode.isVisible = true

                // 통신 Get Code from email
                checkEmailViewModel.email = binding.etSignUpEmail.text.toString().trim()
                checkEmailViewModel.getEmail()
            }
        }
    }

    private fun observeCheckEmailCode(){
        checkEmailViewModel.currentEmail.observe(this, Observer{
            Log.d("code", it.toString())
            emailCode = it.toString()
        })
    }

    private fun checkCode(){
        binding.tvCheckCode.setOnClickListener {
            // check if code is right
            if(emailCode.equals(binding.etEmailCode.text.toString().trim())){
                checkEmailCode = true
                Toast.makeText(context, "인증에 성공했습니다", Toast.LENGTH_SHORT).show()
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
        val signUpActivity = activity as SignUpActivity
        signUpActivity.binding.btnNext.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("email", binding.etSignUpEmail.text.toString())
            bundle.putString("pw", binding.etSignupPw.text.toString())

            signUpActivity.binding.btnNext.text = getString(R.string.finish)
            signUpActivity.binding.btnNext.setBackgroundResource(R.drawable.rectangle_light_gray_radius_30)
            signUpActivity.binding.btnNext.isEnabled = false
            signUpActivity.nextSignUpFragment(NickNameFragment(), bundle)
        }
    }

}