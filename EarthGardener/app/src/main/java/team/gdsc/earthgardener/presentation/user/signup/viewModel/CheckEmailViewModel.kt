package team.gdsc.earthgardener.presentation.user.signup.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.data.model.response.ResCheckEmailSuccessData
import team.gdsc.earthgardener.domain.CheckEmailData
import team.gdsc.earthgardener.domain.LoginRepository

class CheckEmailViewModel(private val loginRepository: LoginRepository): ViewModel() {

    //private var _checkEmail = MutableLiveData<CheckEmailData>()
    //var checkEmail : LiveData<CheckEmailData> = _checkEmail

    private var _email : String = ""
    var email: String = _email
        set(value){
            _email = value
            field = value
        }


    fun getEmail() = viewModelScope.launch(){
        runCatching{loginRepository.getCheckEmailResult(_email)}
            .onSuccess {
                //_checkEmail.postValue(it)
                Log.d("getEmail", it.code)

            }
            .onFailure {
                Log.d("getEmail", "fail")
                it.printStackTrace()
            }
    }
}