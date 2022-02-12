package team.gdsc.earthgardener.presentation.user.login.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.data.model.request.signin.ReqSignInSuccessData
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.X_AUTH_TOKEN
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.editor
import team.gdsc.earthgardener.domain.signin.SignInRepository
import team.gdsc.earthgardener.presentation.user.login.LoginActivity

class SignInViewModel(private val signInRepository: SignInRepository)
    :ViewModel(){

    private val _isSignIn = MutableLiveData<Boolean>()
    val isSignIn: LiveData<Boolean> = _isSignIn


    private var _email: String = ""
    var email: String = _email
        set(value){
            _email = value
            field = value
        }

    private var _pw: String = ""
    var pw: String = _pw
        set(value){
            _pw = value
            field = value
        }

    fun postSignIn() = viewModelScope.launch {
        runCatching{signInRepository.postSignInResult(ReqSignInSuccessData(_email, _pw))}
            .onSuccess {
                Log.d("token", it.token)
                Log.d("post-signin-server 성공", "${it}")
                editor.putString(X_AUTH_TOKEN, it.token)
                editor.commit()
                _isSignIn.postValue(true)
            }
            .onFailure {
                _isSignIn.postValue(false)
                it.printStackTrace()
            }
    }
}