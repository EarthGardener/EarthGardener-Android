package team.gdsc.earthgardener.presentation.user.kakaologin.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.domain.email.CheckEmailRepository
import team.gdsc.earthgardener.util.safeApiCall

class KakaoSignUpViewModel(private val checkEmailRepository: CheckEmailRepository): ViewModel() {

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


}