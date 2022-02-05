package team.gdsc.earthgardener.presentation.post

import android.os.Bundle
import android.util.Log
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivityPostFormBinding
import team.gdsc.earthgardener.domain.post.CheckListData
import team.gdsc.earthgardener.presentation.base.BaseActivity
import team.gdsc.earthgardener.presentation.post.viewmodel.PostFormViewModel

class PostFormActivity : BaseActivity<ActivityPostFormBinding>(R.layout.activity_post_form){
    private val postFormViewModel: PostFormViewModel by viewModel()
    private lateinit var checkList : List<CheckListData.CheckList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postFormViewModel.setCheckList()
        checkList = listOf()
        postFormViewModel.checklist.observe(this) {
            checkList = it.data
            binding.tvFirstCheckList.text = checkList[0].ment
            binding.tvSecondCheckList.text = checkList[1].ment
            binding.tvThirdCheckList.text = checkList[2].ment
            binding.tvForthCheckList.text = checkList[3].ment
            binding.tvFifthCheckList.text = checkList[4].ment
        }

        binding.ivGotoPrev.setOnClickListener {
            // how to....................
        }

        binding.tvSave.setOnClickListener {
            // 뒤로 돌아가라....
        }

/*
        postFormViewModel.checklist.observe(this) {
            binding.checkList = postFormViewModel.checklist.value
        }
 */
    }
}