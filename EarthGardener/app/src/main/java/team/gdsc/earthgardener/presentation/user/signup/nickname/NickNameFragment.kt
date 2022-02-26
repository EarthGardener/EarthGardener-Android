package team.gdsc.earthgardener.presentation.user.signup.nickname

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.FragmentNickNameBinding
import team.gdsc.earthgardener.presentation.base.BaseFragment
import team.gdsc.earthgardener.presentation.user.signup.SignUpActivity
import team.gdsc.earthgardener.presentation.user.signup.retrofit.SignUpRetrofitInterface
import team.gdsc.earthgardener.presentation.user.signup.viewModel.SignUpViewModel
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class NickNameFragment : BaseFragment<FragmentNickNameBinding>(R.layout.fragment_nick_name) {

    private val signUpViewModel : SignUpViewModel by sharedViewModel()

    private val OPEN_GALLERY = 1

    private var email: String? =null
    private var pw: String? = null
    private var check_img = false

    var signUpMap = HashMap<String, RequestBody>()
    var img : MultipartBody.Part?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEmailPw()
        openGallery()
        btnFinishActive()
        observeCheckNickname()
        btnFinishEvent()
        observeSignUp()
    }

    private fun initEmailPw(){
        email = arguments!!.getString("email", "error").trim()
        pw = arguments!!.getString("pw", "error").trim()
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
            MultipartBody.Part.createFormData("image", ".png", bitmapRequestBody)

        img = bitmapMultipartBody
        if(check_img){
            signUpViewModel.image = img!!
            signUpViewModel.postSignUp()
        }
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
            // 먼저 닉네임 중복 여부 판단
            signUpViewModel.nickname = binding.etSignUpNickname.text.toString().trim()
            signUpViewModel.getNickname()
        }
    }

    private fun observeCheckNickname(){
        signUpViewModel.nicknameStatus.observe(this, Observer{
            if(it == 200){
                // 회원가입 post하기
                    var requestEmail = RequestBody.create("text/plain".toMediaTypeOrNull(), email.toString())
                    var requestPW = RequestBody.create("text/plain".toMediaTypeOrNull(), pw.toString())
                    var requestNickname = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.etSignUpNickname.text.toString().trim())

                signUpMap["email"] = requestEmail
                signUpMap["pw"] = requestPW
                signUpMap["nickname"] = requestNickname

                signUpViewModel.map = signUpMap
                // 이미지 여부 판단
                if(img == null){
                    // 기본이미지
                    check_img = true
                    val resources: Resources = this.resources
                    val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_gallery)
                    changeToMultipart(bitmap)
                }else{
                    check_img = false

                    signUpViewModel.image = img!!
                    signUpViewModel.postSignUp()
                }

            }else if(it == 409){
                Toast.makeText(context, "이미 존재하는 닉네임입니다", Toast.LENGTH_SHORT).show()
                binding.etSignUpNickname.text.clear()
            }
        })
    }

    private fun observeSignUp(){
        signUpViewModel.isSignUp.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(context, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()
                val signUpActivity = activity as SignUpActivity
                signUpActivity.finish()
            }
        }
    }

}