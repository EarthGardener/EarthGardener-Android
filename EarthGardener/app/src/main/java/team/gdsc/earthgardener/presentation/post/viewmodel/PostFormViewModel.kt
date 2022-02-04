package team.gdsc.earthgardener.presentation.post.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import team.gdsc.earthgardener.domain.post.CheckListData
import team.gdsc.earthgardener.domain.post.PostListData
import team.gdsc.earthgardener.domain.post.PostRepository

class PostFormViewModel(val postRepository: PostRepository) : ViewModel() {
    private val _checklist = MutableLiveData<CheckListData>()
    val checklist: MutableLiveData<CheckListData> get() = _checklist

    //private val _postList : MutableList<PostListData.PostList> = mutableListOf()
    // postList: MutableList<PostListData.PostList> get() = _postList

    fun getCheckList() : MutableLiveData<CheckListData> {
        return _checklist
    }
}