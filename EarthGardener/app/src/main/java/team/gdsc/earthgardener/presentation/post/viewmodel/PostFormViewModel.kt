package team.gdsc.earthgardener.presentation.post.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.domain.post.CheckListData
import team.gdsc.earthgardener.domain.post.PostListData
import team.gdsc.earthgardener.domain.post.PostRepository

class PostFormViewModel(val postRepository: PostRepository) : ViewModel() {
    private val _checklist = MutableLiveData<CheckListData>()
    val checklist: LiveData<CheckListData> get() = _checklist

    private val _postlist = MutableLiveData<PostListData>()
    val postlist: LiveData<PostListData> get() = _postlist

    //private val _postList : MutableList<PostListData.PostList> = mutableListOf()
    // postList: MutableList<PostListData.PostList> get() = _postList


    fun getCheckList() : MutableLiveData<CheckListData> {
        Log.d("viewModel getCheckList", "${_checklist}")
        return _checklist
    }

    fun setPostList() : MutableLiveData<PostListData> {
        return _postlist
    }
/*
    fun getPostList() = viewModelScope.launch {
        kotlin.runCatching { postRepository.getPostList(date = "2022-02") }
            .onSuccess {
                _postlist.postValue(it)
                Log.d("post-postlist-server 성공", "${it}")
            }
            .onFailure {
                it.printStackTrace()
                Log.d("post-postlist-server 성공", "$it")
            }
    }
*/
    fun setCheckList() = viewModelScope.launch {
        kotlin.runCatching { postRepository.getCheckList() }
            .onSuccess {
                _checklist.postValue(it)
                Log.d("post-checklist-server 성공", "${it.data}")
            }
            .onFailure {
                it.printStackTrace()
                Log.d("post-checklist-server 실패", "$it")
            }
    }
}