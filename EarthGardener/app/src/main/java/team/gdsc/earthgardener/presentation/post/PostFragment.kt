package team.gdsc.earthgardener.presentation.post

import android.os.Bundle
import android.util.Log
import android.view.View
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.FragmentPostBinding
import team.gdsc.earthgardener.presentation.base.BaseFragment
import team.gdsc.earthgardener.presentation.main.viewmodel.MainViewModel
import team.gdsc.earthgardener.presentation.post.viewmodel.PostFormViewModel

class PostFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {

    private lateinit var postListAdapter:PostListAdapter
    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getPostList()
        initPostListAdapter()
        attachAdapter()
    }

    private fun attachAdapter() {
        postListAdapter = PostListAdapter()
        mainViewModel.postlist.observe(viewLifecycleOwner) {
            Log.d("fragment-ovserve", "${it}")
            Log.d("fragment-ovserve-it.data", "${it.data}")
            postListAdapter.postList = it.data
            Log.d("fragemnt-adapter-postlist", "${postListAdapter.postList}")
        }
    }

    private fun initPostListAdapter() {
        postListAdapter = PostListAdapter()
        Log.d("adpater 붙이니???", "adpater 붙이니???")
        binding.rcvRecordlist.adapter = postListAdapter
        Log.d("adpater 붙이니2222???", "adpater 붙이니2222???")
        //postListAdapter.postList = postFormViewModel.postlist

    }

}