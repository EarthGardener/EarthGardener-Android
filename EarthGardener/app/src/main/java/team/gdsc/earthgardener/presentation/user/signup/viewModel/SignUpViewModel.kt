package team.gdsc.earthgardener.presentation.user.signup.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
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

    private var _email : String = ""
    var email: String = _email
        set(value){
            _email = value
            field = value
        }

    // nickname

    private var _status = MutableLiveData<String>()
    var currentStatus : LiveData<String> = _status

    private var _nickname: String = ""
    var nickname: String = _nickname
        set(value){
            _nickname = value
            field = value
        }


    // signup

    private val _isSignUp = MutableLiveData<Boolean>()
    val isSignUp : LiveData<Boolean> = _isSignUp

    private lateinit var _map : HashMap<String, RequestBody>
    var map : HashMap<String, RequestBody> = _map
        set(value){
            _map = value
            field = value
        }



    private lateinit var _image : MultipartBody.Part
    var image : MultipartBody.Part = _image
        set(value){
            _image = value
            field = value
        }



    fun getEmail() = viewModelScope.launch{
        runCatching{checkEmailRepository.getCheckEmailResult(_email)}
            .onSuccess {
                Log.d("getemail", "success")
                //_currentEmail.value = it.code
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getNickname() = viewModelScope.launch{
        kotlin.runCatching { checkNicknameRepository.getCheckNicknameResult(_nickname) }
            .onSuccess {
                Log.d("stauts", it.status.toString())
                _status.value = it.status.toString()
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun postSignUp() = viewModelScope.launch {
        runCatching{ signUpRepository.postSignUpRepositoryResult(_map, _image)}
            .onSuccess {
                Log.d("signup" , "success")
                _isSignUp.postValue(true)
            }
            .onFailure {
                _isSignUp.postValue(false)
                it.printStackTrace()
            }
    }



}