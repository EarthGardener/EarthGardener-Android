package team.gdsc.earthgardener.presentation.user.signup.nickname.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.domain.nickname.CheckNicknameRepository

class CheckNicknameViewModel(private val checkNicknameRepository: CheckNicknameRepository)
    :ViewModel(){

    private val _status = MutableLiveData<String>()
    val currentStatus : LiveData<String>
        get() = _status
    init{
        _status.value = ""
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
                Log.d("stauts", it.status.toString())
                _status.value = it.status.toString()
            }
            .onFailure {
                it.printStackTrace()
            }
    }
}