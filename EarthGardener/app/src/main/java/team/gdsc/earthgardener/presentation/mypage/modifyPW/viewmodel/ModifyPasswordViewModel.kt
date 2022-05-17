package team.gdsc.earthgardener.presentation.mypage.modifyPW.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.data.model.request.password.ReqModifyPasswordSuccessData
import team.gdsc.earthgardener.domain.profile.modifypassword.ModifyPasswordRepository
import team.gdsc.earthgardener.domain.profile.modifypassword.usecase.ModifyPasswordUseCase
import team.gdsc.earthgardener.util.ResultWrapper
import team.gdsc.earthgardener.util.safeApiCall
import timber.log.Timber

class ModifyPasswordViewModel(
    private val modifyPasswordUseCase: ModifyPasswordUseCase
): ViewModel() {

    private var _currentStatus = MutableLiveData<Int>()
    val currentStatus : LiveData<Int> = _currentStatus

    fun putPassword(data: ReqModifyPasswordSuccessData) = viewModelScope.launch {
        when(val responseData =
            safeApiCall(Dispatchers.IO){ modifyPasswordUseCase(data)}){
            is ResultWrapper.Success -> {
                Timber.d("비밀번호 변경 성공")
                _currentStatus.value = 200
            }
            is ResultWrapper.NetworkError -> {
                Timber.d("비밀번호 변경: Network Err")
            }
            is ResultWrapper.GenericError -> {
                Timber.d("비밀번호 변경: Generic Err")
                _currentStatus.value = responseData.code ?: 0
            }
        }
    }
}