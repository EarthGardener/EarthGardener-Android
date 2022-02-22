package team.gdsc.earthgardener.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
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
import team.gdsc.earthgardener.presentation.mypage.modifyProfile.ModifyProfileActivity
import team.gdsc.earthgardener.presentation.user.login.LoginActivity


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page){

    private val profileModel: MainViewModel by sharedViewModel()
    private var nickname : String? = null
    private var img: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileModel.getProfile()
        observeProfile()
        navigateToModifyProfile()
    }

    private fun observeProfile(){
        profileModel.profile.observe(viewLifecycleOwner){
            binding.tvMyPageNickname.text = it.nickname
            Glide.with(context!!)
                .load("http://52.78.175.39:8080" + it.image_url)
                .into(binding.ivMyPageUser)

            nickname = it.nickname
            img = it.image_url
        }
    }

    private fun navigateToModifyProfile(){
        binding.tvModifyProfile.setOnClickListener {
            val intent = Intent(context, ModifyProfileActivity::class.java)
            intent.putExtra("nickname", nickname)
            intent.putExtra("img", img)
            startActivity(intent)
        }
    }
}