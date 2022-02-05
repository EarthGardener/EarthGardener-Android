package team.gdsc.earthgardener.presentation.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.FragmentMyPageBinding
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.X_ACCESS_TOKEN
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.sSharedPreferences
import team.gdsc.earthgardener.presentation.base.BaseFragment
import team.gdsc.earthgardener.presentation.mypage.retrofit.ProfileResponse
import team.gdsc.earthgardener.presentation.user.signup.retrofit.SignUpRetrofitClient

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getProfileData(sSharedPreferences.getString(X_ACCESS_TOKEN, null)!!)
    }


    private fun getProfileData(token: String){
        val profileInterface = SignUpRetrofitClient.sRetrofit.create(MyPageRetrofitInterface::class.java)

        profileInterface.getProfile(token).enqueue(object: Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if(response.isSuccessful){
                    binding.tvMyPageNickname.text = response.body()?.data?.nickname
                    Glide.with(context!!)
                        .load("http://52.78.175.39:8080" + response.body()?.data?.image_url)
                        .into(binding.ivMyPageUser)
                }else{
                    Log.d("fail", "error code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.d("onFailure", t.message ?: "통신오류")
            }

        })
    }
}