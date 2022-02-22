package team.gdsc.earthgardener.presentation.mypage.modifyProfile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.bumptech.glide.Glide
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivityModifyProfileBinding
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.user.signup.nickname.NickNameFragment
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.*

class ModifyProfileActivity : BaseActivity<ActivityModifyProfileBinding>(R.layout.activity_modify_profile) {

    private val OPEN_GALLERY = 1

    var newNickname : String ?= null
    var newImg : MultipartBody.Part?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setProfile()
        openGallery()
        btnModifyActive()
        putProfile()

        Log.d("img", binding.ivModifyProfileUser.toString())
    }


    private fun setProfile(){
        binding.etModifyProfileNickname.setText(intent.getStringExtra("nickname"))
        Glide.with(this)
            .load("http://52.78.175.39:8080" + intent.getStringExtra("img"))
            .into(binding.ivModifyProfileUser)
    }

    private fun openGallery(){
        binding.ivModifyProfileUser.setOnClickListener {
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
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)
                    binding.ivModifyProfileUser.setImageBitmap(bitmap)
                    changeToMultipart(bitmap)
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    private fun changeToMultipart(bitmap: Bitmap){
        val bitmapRequestBody = NewBitmapRequestBody(bitmap)
        val bitmapMultipartBody: MultipartBody.Part =
            MultipartBody.Part.createFormData("image", ".png", bitmapRequestBody)

        newImg = bitmapMultipartBody
        // Post bitmapMultipartBody
    }

    inner class NewBitmapRequestBody(private val bitmap: Bitmap): RequestBody(){
        override fun contentType(): MediaType = "image/jpeg".toMediaType()

        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream())
        }

    }

    private fun btnModifyActive(){
        binding.etModifyProfileNickname.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.etModifyProfileNickname.text.isNotEmpty()){
                    binding.btnModifyProfile.setBackgroundResource(R.drawable.rectangle_primary_green_radius_30)
                    binding.btnModifyProfile.isEnabled = true
                }else{
                    binding.btnModifyProfile.setBackgroundResource(R.drawable.rectangle_light_gray_radius_30)
                    binding.btnModifyProfile.isEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun putProfile(){
        binding.btnModifyProfile.setOnClickListener {
            // 새로운 이미지 => bitmap보내고 아니면 이전 img_url 보내기

            // put

            // 성공시 finish()
        }
    }


}