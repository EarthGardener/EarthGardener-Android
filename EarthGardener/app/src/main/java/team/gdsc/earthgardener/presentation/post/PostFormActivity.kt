package team.gdsc.earthgardener.presentation.post

import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivityPostFormBinding
import team.gdsc.earthgardener.domain.post.CheckListData
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.post.viewmodel.PostFormViewModel

class PostFormActivity : BaseActivity<ActivityPostFormBinding>(R.layout.activity_post_form){
    private val postFormViewModel: PostFormViewModel by viewModel()
    private lateinit var checkList : CheckListData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postFormViewModel.setCheckList()
        postFormViewModel.getCheckList()

        checkList = postFormViewModel.checklist.value!!


        binding.tvFirstCheckList.text = checkList.data[0].ment
        binding.tvSecondCheckList.text = checkList.data[1].ment
        binding.tvThirdCheckList.text = checkList.data[2].ment
        binding.tvForthCheckList.text = checkList.data[3].ment
        binding.tvFifthCheckList.text = checkList.data[4].ment
/*
        postFormViewModel.checklist.observe(this) {
            binding.checkList = postFormViewModel.checklist.value
        }
 */
    }
}