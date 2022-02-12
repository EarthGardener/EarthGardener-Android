package team.gdsc.earthgardener.presentation.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.FragmentMyPageBinding

import team.gdsc.earthgardener.presentation.base.BaseFragment
import team.gdsc.earthgardener.presentation.main.viewmodel.MainViewModel



class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page){

    private val profileModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileModel.getProfile()
        observeProfile()
    }

    private fun observeProfile(){
        profileModel.profile.observe(viewLifecycleOwner){
            binding.tvMyPageNickname.text = it.nickname
            Glide.with(context!!)
                .load("http://52.78.175.39:8080" + it.image_url)
                .into(binding.ivMyPageUser)
        }
    }
}