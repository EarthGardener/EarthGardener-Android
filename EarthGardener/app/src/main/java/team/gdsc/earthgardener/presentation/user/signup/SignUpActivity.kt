package team.gdsc.earthgardener.presentation.user.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivitySignUpBinding
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.user.signup.emailpw.EmailPwFragment

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSignUpFragment()
    }

    private fun setSignUpFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_sign_up, EmailPwFragment())
            .commit()
    }

    fun nextSignUpFragment(fa: Fragment, bundle: Bundle){
        fa.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_sign_up, fa)
            .commit()
    }

}