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
    private val checkList = CheckListData.CheckList(id = 0, ment = "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postFormViewModel.getCheckList()

        postFormViewModel.checklist.observe(this) {
            binding.checkList = postFormViewModel.checklist.value
        }
    }
}