package team.gdsc.earthgardener.presentation.mypage.modifyProfile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import okio.BufferedSink
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivityModifyProfileBinding
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.X_AUTH_TOKEN
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.sSharedPreferences
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.mypage.modifyProfile.retrofit.ModifyProfileResponse
import team.gdsc.earthgardener.presentation.mypage.modifyProfile.retrofit.ModifyProfileRetrofitInterface
import team.gdsc.earthgardener.presentation.mypage.modifyProfile.viewmodel.ModifyProfileViewModel
import team.gdsc.earthgardener.presentation.user.signup.retrofit.RetrofitClient
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ModifyProfileActivity : BaseActivity<ActivityModifyProfileBinding>(R.layout.activity_modify_profile) {

    //private val modifyProfileModel : ModifyProfileViewModel by viewModel()

    private val OPEN_GALLERY = 1

    var newNickname : String ?= null
    var newImg : MultipartBody.Part?= null
    var profileMap = HashMap<String, RequestBody>()
    var check_img = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setProfile()
        openGallery()
        btnModifyActive()
        putProfile()
        //observeProfile()
        close()
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

                    binding.btnModifyProfile.setBackgroundResource(R.drawable.rectangle_primary_green_radius_30)
                    binding.btnModifyProfile.isEnabled = true
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
        Log.d("bitmap_", newImg.toString())
        if(check_img){
            getProfileData(sSharedPreferences.getString(X_AUTH_TOKEN, null)!!, newImg!!, profileMap)
        }
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

    private fun convertBitmapFromURL(url: String): Bitmap?{
        try {
            val url = URL(url)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream

            return BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    private fun putProfile(){
        binding.btnModifyProfile.setOnClickListener {
            newNickname = binding.etModifyProfileNickname.text.toString()
            var nickname = RequestBody.create("text/plain".toMediaTypeOrNull(),newNickname!!)
            profileMap["nickname"] = nickname


            // 새로운 이미지 => bitmap보내고 아니면 이전 img_url 보내기
            if(newImg == null){
                check_img = true

                // 이미지 변경 없는 경우
                lifecycleScope.launch(Dispatchers.IO){
                    val bitmap = convertBitmapFromURL("http://52.78.175.39:8080" + intent.getStringExtra("img"))
                    changeToMultipart(bitmap!!)
                }

            }else{
                check_img = false
                getProfileData(sSharedPreferences.getString(X_AUTH_TOKEN, null)!!, newImg!!, profileMap)
            }
            
            // put
            //modifyProfileModel.image = newImg!!
            //modifyProfileModel.map = profileMap
            //modifyProfileModel.putProfile()

            // 성공시 finish()
        }
    }

    /*
    private fun observeProfile(){
        modifyProfileModel.status.observe(this, Observer {
            if(it.toString() == "200"){
                Toast.makeText(this, "회원정보 수정 성공", Toast.LENGTH_SHORT).show()
                finish()
            }else if(it.toString() == "409"){
                Toast.makeText(this, "이미지 크기가 큽니다", Toast.LENGTH_SHORT).show()
            }
        })
    }

     */

    private fun getProfileData(token: String, image: MultipartBody.Part, data: HashMap<String, RequestBody>){
        val modifyProfilInterface = RetrofitClient.sRetrofit.create(ModifyProfileRetrofitInterface::class.java)

        modifyProfilInterface.putProfile(token, image, data).enqueue(object: Callback<ModifyProfileResponse> {
            override fun onResponse(
                call: Call<ModifyProfileResponse>,
                response: Response<ModifyProfileResponse>
            ) {
                if(response.isSuccessful){
                    Log.d("회원정보 수정", "success")
                    finish()
                }else{
                    Log.d("회원정보 수정", "fail ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ModifyProfileResponse>, t: Throwable) {
                Log.d("signup", t.message ?: "통신오류")
            }

        })
    }

    private fun close(){
        binding.ivFinish.setOnClickListener { finish() }
    }

}