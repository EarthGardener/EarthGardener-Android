package team.gdsc.earthgardener.presentation.user.login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.data.model.request.signin.ReqKakaoSignInSuccessData
import team.gdsc.earthgardener.data.model.request.signin.ReqSignInSuccessData
import team.gdsc.earthgardener.databinding.ActivityLoginBinding
import team.gdsc.earthgardener.presentation.main.MainActivity
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.user.login.viewModel.SignInViewModel
import team.gdsc.earthgardener.presentation.user.signup.SignUpActivity
import java.util.regex.Pattern

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val signInViewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        btnLoginEvent()
        navigateToSignUp()
        observeSignIn()
        observeKakaoSignIn()
        btnKakaoLoginEvent()
        checkKakaoLogin()
        //logout()
    }

    private fun btnLoginEvent(){
        binding.btnLogin.setOnClickListener {
            if(checkEmailPattern()){
                val email = binding.etLoginEmail.text.toString().trim()
                val pw = binding.etLoginPw.text.toString().trim()

                showLoadingDialog(this)
                // Post Login
                signInViewModel.postSignIn(ReqSignInSuccessData(email, pw))
            }else{
                Toast.makeText(this, "이메일 형식에 맞추어 작성해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToSignUp(){
        binding.tvLoginSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun navigateToMain(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun observeSignIn(){
        signInViewModel.signInStatus.observe(this) {
            dismissLoadingDialog()
            if (it == 200) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                navigateToMain()
            } else if (it == 401) {
                Toast.makeText(this, "이메일 또는 비밀번호 오류", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeKakaoSignIn(){
        signInViewModel.kakaoSignInStatus.observe(this){
            dismissLoadingDialog()
            if (it == 200) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                navigateToMain()
            } else if (it == 401) {
                Toast.makeText(this, "소셜 로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun checkEmailPattern(): Boolean {
        val email = binding.etLoginEmail.text.toString().trim()
        val emailValidation =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        return Pattern.matches(emailValidation, email)
    }

    private fun btnKakaoLoginEvent(){
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if(error != null){
                Log.d("login", "정보 없음")
            }else{
                Log.d("login", "카카오톡 로그인에 성공")

                UserApiClient.instance.me{ user, error ->
                    val id = user?.id.toString()
                    val nickname = user?.kakaoAccount?.profile?.nickname.toString()
                    var image = user?.kakaoAccount?.profile?.profileImageUrl

                    if(image.isNullOrEmpty()){
                        image = ""
                    }

                    Log.d("id", id)
                    Log.d("nickname", nickname)
                    Log.d("image",image)

                    // Post
                    showLoadingDialog(this)
                    signInViewModel.postKakaoSignIn(ReqKakaoSignInSuccessData(id, nickname, image))
                }
            }
        }


        binding.btnKakaoLogin.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    private fun checkKakaoLogin(){
        UserApiClient.instance.accessTokenInfo{ tokenInfo, error ->
            if(error != null){
                Log.d("login", "로그인 정보 없음")
            }else if(tokenInfo != null){
                // 이메일 받기 뷰로 이동
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }
    }

    private fun logout(){
        binding.btnKakaoLogin.setOnClickListener {
            //회원 탈퇴 - 임시
            UserApiClient.instance.unlink { error ->
                if(error != null){
                    Toast.makeText(this, "회원 탈퇴 실패", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "회원 탈퇴 성공", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}