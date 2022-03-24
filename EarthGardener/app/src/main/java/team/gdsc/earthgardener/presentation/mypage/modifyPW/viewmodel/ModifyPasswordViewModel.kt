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
import team.gdsc.earthgardener.domain.profile.modifypassword.usecase.ModifyPwUseCase
import team.gdsc.earthgardener.util.ResultWrapper
import team.gdsc.earthgardener.util.safeApiCall
import timber.log.Timber

class ModifyPasswordViewModel(
    private val modifyPasswordUseCase: ModifyPwUseCase
): ViewModel() {

    var currentStatus = MutableLiveData<Int>()

    fun putPassword(data: ReqModifyPasswordSuccessData) = viewModelScope.launch {
        when(val responseData =
            safeApiCall(Dispatchers.IO){modifyPasswordUseCase(data)}){
            is ResultWrapper.Success -> {
                Timber.d("putPassword : Success")
                currentStatus.value = responseData.data.status
            }
            is ResultWrapper.NetworkError -> {
                Timber.d("putPassword : Network Err")
            }
            is ResultWrapper.GenericError -> {
                Timber.d("putPassword : Generic Err")
                currentStatus.value = responseData.code ?: 0
            }
        }
    }
}