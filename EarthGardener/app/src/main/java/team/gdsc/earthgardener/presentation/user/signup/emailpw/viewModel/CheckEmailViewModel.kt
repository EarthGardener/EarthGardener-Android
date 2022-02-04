package team.gdsc.earthgardener.presentation.user.signup.emailpw.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.domain.email.CheckEmailRepository

class CheckEmailViewModel(private val checkEmailRepository: CheckEmailRepository): ViewModel() {

    private val _currentEmail = MutableLiveData<String>()
    val currentEmail : LiveData<String>
        get() = _currentEmail
    init{
        _currentEmail.value = ""
    }

    private var _email : String = ""
    var email: String = _email
        set(value){
            _email = value
            field = value
        }

    fun getEmail() = viewModelScope.launch(){
        runCatching{checkEmailRepository.getCheckEmailResult(_email)}
            .onSuccess {
                _currentEmail.value = it.code
            }
            .onFailure {
                it.printStackTrace()
            }
    }
}