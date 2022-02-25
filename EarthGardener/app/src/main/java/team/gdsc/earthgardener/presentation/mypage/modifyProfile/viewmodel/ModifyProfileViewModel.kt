package team.gdsc.earthgardener.presentation.mypage.modifyProfile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import team.gdsc.earthgardener.domain.profile.modify.ModifyProfileRepository

class ModifyProfileViewModel(
    private val modifyProfileRepository: ModifyProfileRepository
) : ViewModel(){

    private var _status = MutableLiveData<Int>()
    val status : LiveData<Int> = _status

    val tempImg : RequestBody = RequestBody.create("image/jpg".toMediaTypeOrNull(), "temp")
    private var _map : HashMap<String, RequestBody> = hashMapOf("temp" to tempImg)
    var map: HashMap<String, RequestBody> = _map
        set(value){
            _map = value
            field = value
        }

    private var _image: MultipartBody.Part = MultipartBody.Part.createFormData("file", "file")
    var image : MultipartBody.Part = _image
        set(value){
            _image = value
            field = value
        }


    fun putProfile() = viewModelScope.launch {
        runCatching{ modifyProfileRepository.putModifyProfileResult(_image, _map)}
            .onSuccess {
                _status.postValue(it.status)
                Log.d("회원정보 수정", "success")
            }
            .onFailure {
                Log.d("회원정보 수정", "fail")
                it.printStackTrace()
            }
    }

}