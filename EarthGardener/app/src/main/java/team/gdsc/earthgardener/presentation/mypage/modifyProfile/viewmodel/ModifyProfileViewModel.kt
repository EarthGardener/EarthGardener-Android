package team.gdsc.earthgardener.presentation.mypage.modifyProfile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.gdsc.earthgardener.domain.profile.modifyprofile.ModifyProfileRepository
import team.gdsc.earthgardener.domain.profile.modifyprofile.usecase.ModifyProfileUseCase
import team.gdsc.earthgardener.util.ResultWrapper
import team.gdsc.earthgardener.util.safeApiCall
import timber.log.Timber

class ModifyProfileViewModel(
    private val modifyProfileUseCase: ModifyProfileUseCase
) : ViewModel(){

    private var _status = MutableLiveData<Int>()
    val status : LiveData<Int> = _status

    fun putProfile(image: MultipartBody.Part,data: HashMap<String, RequestBody>) = viewModelScope.launch {
        when(val responseData =
            safeApiCall(Dispatchers.IO){modifyProfileUseCase(image, data)}){
            is ResultWrapper.Success ->{
                Timber.d("프로필 변경 성공")
                _status.value = 200
            }
            is ResultWrapper.NetworkError -> {
                Timber.d("프로필 변경: Network Err")
            }
            is ResultWrapper.GenericError -> {
                Timber.d("프로필 변경: Generic ERr")
                _status.value = responseData.code ?: 0
            }
        }
    }

}