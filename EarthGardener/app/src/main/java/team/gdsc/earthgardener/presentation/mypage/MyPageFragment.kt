package team.gdsc.earthgardener.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.FragmentMyPageBinding

import team.gdsc.earthgardener.presentation.base.BaseFragment
import team.gdsc.earthgardener.presentation.main.viewmodel.MainViewModel
import team.gdsc.earthgardener.presentation.mypage.modifyPW.ModifyPwActivity
import team.gdsc.earthgardener.presentation.mypage.modifyProfile.ModifyProfileActivity


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page){

    private val profileModel: MainViewModel by sharedViewModel()
    private var nickname : String? = null
    private var img: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileModel.getProfile()
        observeProfile()
        navigateToModifyProfile()
        navigateToModifyPW()
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

    private fun navigateToModifyPW(){
        binding.tvModifyPw.setOnClickListener {
            val intent = Intent(context, ModifyPwActivity::class.java)
            startActivity(intent)
        }
    }
}