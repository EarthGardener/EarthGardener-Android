package team.gdsc.earthgardener.presentation.user.kakaologin.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.data.model.request.signin.ReqKakaoSignInSuccessData
import team.gdsc.earthgardener.di.EarthGardenerApplication
import team.gdsc.earthgardener.domain.email.CheckEmailRepository
import team.gdsc.earthgardener.domain.signin.kakao.KakaoSignInRepository
import team.gdsc.earthgardener.domain.signin.kakao.usecase.KakaoSignInUseCase
import team.gdsc.earthgardener.util.ResultWrapper
import team.gdsc.earthgardener.util.safeApiCall
import timber.log.Timber

class KakaoSignUpViewModel(
    private val checkEmailRepository: CheckEmailRepository,
    private val kakaoSignInUseCase: KakaoSignInUseCase
    ): ViewModel() {

    // email code
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

    // kakao login
    private val _kakaoSignInStatus = MutableLiveData<Int>()
    val kakaoSignInStatus : LiveData<Int> = _kakaoSignInStatus

    fun getEmail() = viewModelScope.launch {
        kotlin.runCatching { checkEmailRepository.getCheckEmailResult(_email) }
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

    fun postKakaoSignIn(data: ReqKakaoSignInSuccessData) = viewModelScope.launch {
        when (val responseData =
            safeApiCall(Dispatchers.IO){kakaoSignInUseCase(data)}){
            is ResultWrapper.Success ->{
                Timber.d("postKakaoSignIn : Success")
                _kakaoSignInStatus.value = 200
                EarthGardenerApplication.editor.putString(EarthGardenerApplication.X_AUTH_TOKEN, responseData.data.token)
                Log.d("kakao-login token", responseData.data.token)
                EarthGardenerApplication.editor.commit()
            }
            is ResultWrapper.NetworkError -> {
                Timber.d("postKakaoSignIn : Network Err")
            }
            is ResultWrapper.GenericError -> {
                Timber.d("postKakaoSignIn : Generic Err")
                _kakaoSignInStatus.value = responseData.code ?: 0
            }
        }
    }


}