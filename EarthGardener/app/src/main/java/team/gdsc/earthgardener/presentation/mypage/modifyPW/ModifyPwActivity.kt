package team.gdsc.earthgardener.presentation.mypage.modifyPW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.data.model.request.password.ReqModifyPasswordSuccessData
import team.gdsc.earthgardener.databinding.ActivityModifyPwBinding
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.mypage.modifyPW.viewmodel.ModifyPasswordViewModel

class ModifyPwActivity : BaseActivity<ActivityModifyPwBinding>(R.layout.activity_modify_pw) {

    private val modifyPasswordModel: ModifyPasswordViewModel by viewModel()
    private var ori_pw: String ?= null
    private var new_pw: String ?= null

    var checkOriginalPw = false
    var checkNewPw = false
    var checkConfirmNewPw = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkOriginalPw()
        checkNewPw()
        checkConfirmNewPw()
        modifyPW()
        observePassword()
        close()
    }

    private fun btnModifyActive(){
        if(checkOriginalPw && checkNewPw && checkConfirmNewPw){
            binding.btnModifyPw.setBackgroundResource(R.drawable.rectangle_primary_green_radius_30)
            binding.btnModifyPw.isEnabled = true
        }else{
            binding.btnModifyPw.setBackgroundResource(R.drawable.rectangle_light_gray_radius_30)
            binding.btnModifyPw.isEnabled = false
        }
    }

    private fun checkOriginalPw(){
        binding.etModifyOriginalPw.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkOriginalPw = binding.etModifyOriginalPw.text.isNotEmpty()
                btnModifyActive()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun checkConfirmNewPw(){
        binding.etModifyConfirmNewPw.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkConfirmNewPw = binding.etModifyConfirmNewPw.text.isNotEmpty()
                btnModifyActive()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun checkNewPw(){
        binding.etModifyNewPw.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkNewPw = binding.etModifyNewPw.text.isNotEmpty()
                btnModifyActive()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun modifyPW(){
        binding.btnModifyPw.setOnClickListener {
            showLoadingDialog(this)
            val check = checkPWIfEqual()
            Log.d("check", check.toString())
            if(check){
                // put
                ori_pw = binding.etModifyOriginalPw.text.toString().trim()
                new_pw = binding.etModifyNewPw.text.toString().trim()
                modifyPasswordModel.putPassword(ReqModifyPasswordSuccessData(ori_pw!!, new_pw!!))
            }else{
                Toast.makeText(this, "새 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPWIfEqual(): Boolean{
        // 새로운 pw == 새로운 pw 확인
        return binding.etModifyNewPw.text.toString().trim().equals(binding.etModifyConfirmNewPw.text.toString().trim())
    }

    private fun observePassword(){
        modifyPasswordModel.currentStatus.observe(this, Observer{
            dismissLoadingDialog()
            if(it == 200){
                // pw 변경 성공
                Toast.makeText(this, "비밀번호가 변경되었습니다", Toast.LENGTH_SHORT).show()
                finish()
            }else if(it == 409){
                // fail
                Toast.makeText(this, "기존 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show()
            }else if(it == 401){
                Toast.makeText(this, "토큰이 만료되었습니다", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun close(){
        binding.ivFinish.setOnClickListener { finish() }
    }
}