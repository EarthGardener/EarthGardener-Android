package team.gdsc.earthgardener.presentation.user.signup.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.gdsc.earthgardener.domain.email.CheckEmailRepository
import team.gdsc.earthgardener.domain.nickname.CheckNicknameRepository
import team.gdsc.earthgardener.domain.signup.SignUpRepository

class SignUpViewModel(
    private val checkEmailRepository: CheckEmailRepository,
    private val checkNicknameRepository: CheckNicknameRepository,
    private val signUpRepository: SignUpRepository
    )
    :ViewModel(){

    // email
    private var _currentCode = MutableLiveData<String>()
    var currentCode : LiveData<String> = _currentCode

    private var _emailStatus = MutableLiveData<Int>()
    var emailStatus : LiveData<Int> = _emailStatus

    private var _email : String = ""
    var email: String = _email
        set(value){
            _email = value
            field = value
        }

    // nickname
    private var _checkNickname = MutableLiveData<Boolean>()
    var checkNickname : LiveData<Boolean> = _checkNickname

    private var _nickname: String = ""
    var nickname: String = _nickname
        set(value){
            _nickname = value
            field = value
        }


    // signup

    private val _isSignUp = MutableLiveData<Boolean>()
    val isSignUp : LiveData<Boolean> = _isSignUp

    val tempImg : RequestBody = RequestBody.create("image/jpg".toMediaTypeOrNull(), "temp")
    private var _map : HashMap<String, RequestBody> = hashMapOf("temp" to tempImg)
    var map : HashMap<String, RequestBody> = _map
        set(value){
            _map = value
            field = value
        }


    private var _image : MultipartBody.Part = MultipartBody.Part.createFormData("file", "file")
    var image : MultipartBody.Part = _image
        set(value){
            _image = value
            field = value
        }



    fun getEmail() = viewModelScope.launch{
        runCatching{checkEmailRepository.getCheckEmailResult(_email)}
            .onSuccess {
                _emailStatus.value = it.status
                Log.d("currentCode", it.code)
                _currentCode.value = it.code
            }
            .onFailure {
                _emailStatus.value = 409
                it.printStackTrace()
            }
    }

    fun getNickname() = viewModelScope.launch{
        kotlin.runCatching { checkNicknameRepository.getCheckNicknameResult(_nickname) }
            .onSuccess {
                if(it.status.toString().equals("200")){
                    Log.d("닉네임", "success")
                    _checkNickname.value = true
                }else{
                    Log.d("닉네임", "중복됨")
                    _checkNickname.value = false
                }
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun postSignUp() = viewModelScope.launch {
        runCatching{ signUpRepository.postSignUpRepositoryResult(_map, _image)}
            .onSuccess {
                Log.d("일반 회원가입", "success")
                _isSignUp.postValue(true)
            }
            .onFailure {
                Log.d("일반 회원가입", "fail")
                _isSignUp.postValue(false)
                it.printStackTrace()
            }
    }
}