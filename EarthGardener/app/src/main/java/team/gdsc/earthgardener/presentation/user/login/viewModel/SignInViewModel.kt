package team.gdsc.earthgardener.presentation.user.login.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.data.model.request.signin.ReqKakaoSignInSuccessData
import team.gdsc.earthgardener.data.model.request.signin.ReqSignInSuccessData
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.X_AUTH_TOKEN
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.editor
import team.gdsc.earthgardener.domain.signin.kakao.usecase.KakaoSignInUseCase
import team.gdsc.earthgardener.domain.signin.usecase.SignInUseCase
import team.gdsc.earthgardener.util.ResultWrapper
import team.gdsc.earthgardener.util.safeApiCall
import timber.log.Timber

class SignInViewModel(private val signInUseCase: SignInUseCase, private val kakaoSignInUseCase: KakaoSignInUseCase)
    :ViewModel(){

    private val _signInStatus = MutableLiveData<Int>()
    val signInStatus : LiveData<Int> = _signInStatus

    private val _kakaoSignInStatus = MutableLiveData<Int>()
    val kakaoSignInStatus : LiveData<Int> = _kakaoSignInStatus

    fun postSignIn(data: ReqSignInSuccessData) = viewModelScope.launch {
       when(val responseData =
           safeApiCall(Dispatchers.IO){ signInUseCase(data)}){
           is ResultWrapper.Success -> {
               Timber.d("postSignIn : Success")
               _signInStatus.value = 200
               Log.d("login token", responseData.data.token)
               editor.putString(X_AUTH_TOKEN, responseData.data.token)
               editor.commit()
           }
           is ResultWrapper.NetworkError -> {
               Timber.d("postSignIn : Network Err")
           }
           is ResultWrapper.GenericError -> {
               Timber.d("postSignIn : Generic Err")
               _signInStatus.value = responseData.code ?: 0
           }
       }
    }

    fun postKakaoSignIn(data: ReqKakaoSignInSuccessData) = viewModelScope.launch {
        when (val responseData =
            safeApiCall(Dispatchers.IO){kakaoSignInUseCase(data)}){
            is ResultWrapper.Success ->{
                Timber.d("postKakaoSignIn : Success")
                _kakaoSignInStatus.value = 200
                editor.putString(X_AUTH_TOKEN, responseData.data.token)
                Log.d("kakao-login token", responseData.data.token)
                editor.commit()
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