package team.gdsc.earthgardener.presentation.user.signup.nickname

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.FragmentNickNameBinding
import team.gdsc.earthgardener.presentation.base.BaseFragment
import team.gdsc.earthgardener.presentation.user.signup.SignUpActivity
import team.gdsc.earthgardener.presentation.user.signup.nickname.viewModel.CheckNicknameViewModel
import java.lang.Exception

class NickNameFragment : BaseFragment<FragmentNickNameBinding>(R.layout.fragment_nick_name) {

    private val checkNicknameViewModel: CheckNicknameViewModel by viewModel()
    private val OPEN_GALLERY = 1

    private var email: String? =null
    private var pw: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openGallery()
        btnFinishActive()
        observeCheckNickname()
        btnFinishEvent()

        email = arguments!!.getString("email", "error")
        pw = arguments!!.getString("pw", "error")
    }

    private fun openGallery(){
        binding.ivSignupUser.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, OPEN_GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == OPEN_GALLERY){
                val currentImageUrl: Uri? = data?.data
                try{
                    val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, currentImageUrl)
                    binding.ivSignupUser.setImageBitmap(bitmap)
                    changeToMultipart(bitmap)
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    private fun changeToMultipart(bitmap: Bitmap){
        val bitmapRequestBody = BitmapRequestBody(bitmap)
        val bitmapMultipartBody: MultipartBody.Part =
            MultipartBody.Part.createFormData("image", ".jpeg", bitmapRequestBody)

        // Post bitmapMultipartBody
    }

    inner class BitmapRequestBody(private val bitmap: Bitmap): RequestBody(){
        override fun contentType(): MediaType = "image/jpeg".toMediaType()

        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream())
        }

    }

    private fun btnFinishActive(){
        binding.etSignUpNickname.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val signUpActivity = activity as SignUpActivity
                if(binding.etSignUpNickname.text.isNotEmpty()){
                    signUpActivity.binding.btnNext.setBackgroundResource(R.drawable.rectangle_primary_green_radius_30)
                    signUpActivity.binding.btnNext.isEnabled = true
                }else{
                    signUpActivity.binding.btnNext.setBackgroundResource(R.drawable.rectangle_light_gray_radius_30)
                    signUpActivity.binding.btnNext.isEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun btnFinishEvent(){
        val signUpActivity = activity as SignUpActivity
        signUpActivity.binding.btnNext.setOnClickListener {
            //signUpActivity.finish()
            // 먼저 닉네임 중복 여부 판단
            checkNicknameViewModel.nickname = binding.etSignUpNickname.text.toString()
            checkNicknameViewModel.getNickname()
        }
    }

    private fun observeCheckNickname(){
        checkNicknameViewModel.currentStatus.observe(this, Observer{
            if(it.toString() == "200"){
                // 회원가입 post하기
                    Log.d("nickname", "new")
                // 회원가입 post 성공하면 로그인 화면으로 이동

            }else{
                Toast.makeText(context, "이미 존재하는 닉네임입니다", Toast.LENGTH_SHORT).show()
                binding.etSignUpNickname.text.clear()
            }
        })
    }
}