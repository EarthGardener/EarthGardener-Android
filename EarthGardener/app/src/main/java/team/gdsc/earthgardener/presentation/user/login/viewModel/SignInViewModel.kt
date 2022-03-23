package team.gdsc.earthgardener.presentation.user.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.data.model.request.signin.ReqSignInSuccessData
import team.gdsc.earthgardener.domain.signin.usecase.SignInUseCase
import team.gdsc.earthgardener.util.ResultWrapper
import team.gdsc.earthgardener.util.safeApiCall
import timber.log.Timber

class SignInViewModel(private val signInUseCase: SignInUseCase)
    :ViewModel(){

    private val _signInStatus = MutableLiveData<Int>()
    val signInStatus : LiveData<Int> = _signInStatus


    fun postSignIn(data: ReqSignInSuccessData) = viewModelScope.launch {
       when(val responseData =
           safeApiCall(Dispatchers.IO){ signInUseCase(data)}){
           is ResultWrapper.Success -> {
               Timber.d("postSignIn : Success")
               _signInStatus.value = 200
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
}