package team.gdsc.earthgardener.presentation.mypage.modifyPW.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.data.model.request.password.ReqModifyPasswordSuccessData
import team.gdsc.earthgardener.domain.profile.modifypassword.ModifyPasswordRepository

class ModifyPasswordViewModel(
    private val modifyPasswordRepository: ModifyPasswordRepository
): ViewModel() {

    private var _currentStatus = MutableLiveData<Int>()
    val currentStatus : LiveData<Int> = _currentStatus

    private var _ori_pw : String = ""
    var ori_pw : String = _ori_pw
        set(value){
            _ori_pw = value
            field = value
        }

    private var _new_pw : String = ""
    var new_pw : String = _new_pw
        set(value){
            _new_pw = value
            field = value
        }

    fun putPassword() = viewModelScope.launch {
        runCatching{ modifyPasswordRepository.putModifyPasswordResult(ReqModifyPasswordSuccessData(_ori_pw, _new_pw))}
            .onSuccess {
                _currentStatus.value = it.status
            }
            .onFailure {
                it.printStackTrace()
                _currentStatus.value = 409
            }
    }
}