package team.gdsc.earthgardener.presentation.user.signup.emailpw.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.domain.nickname.CheckNicknameRepository

class CheckNicknameViewModel(private val checkNicknameRepository: CheckNicknameRepository)
    :ViewModel(){

    private val _status = MutableLiveData<Int>()
    val currentStatus : LiveData<Int>
        get() = _status
    init{
        _status.value = 0
    }


    private var _nickname: String = ""
    var nickname: String = _nickname
        set(value){
            _nickname = value
            field = value
        }


    fun getNickname() = viewModelScope.launch(){
        kotlin.runCatching { checkNicknameRepository.getCheckNicknameResult(_nickname) }
            .onSuccess {

            }
            .onFailure {
                it.printStackTrace()
            }
    }
}