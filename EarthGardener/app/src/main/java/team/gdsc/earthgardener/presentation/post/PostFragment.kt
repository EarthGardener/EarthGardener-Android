package team.gdsc.earthgardener.presentation.post

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivityMainBinding
import team.gdsc.earthgardener.databinding.FragmentPostBinding
import team.gdsc.earthgardener.domain.post.PostListData
import team.gdsc.earthgardener.presentation.base.BaseFragment
import team.gdsc.earthgardener.presentation.post.viewmodel.PostFormViewModel

class PostFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {

    private lateinit var postListAdapter:PostListAdapter
    private val postFormViewModel: PostFormViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPostListAdapter()
        binding.ivWrite.setOnClickListener {
            val intent =Intent(activity, PostFormActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    private fun initPostListAdapter() {
        postListAdapter = PostListAdapter()
        //postListAdapter.postList = postFormViewModel.postList
        binding.rcvRecordlist.adapter = postListAdapter
    }

}