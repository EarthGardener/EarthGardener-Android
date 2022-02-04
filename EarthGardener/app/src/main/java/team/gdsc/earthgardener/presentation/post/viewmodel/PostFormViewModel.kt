package team.gdsc.earthgardener.presentation.post.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
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

    fun setCheckList() = viewModelScope.launch {
        kotlin.runCatching { postRepository.getCheckList() }
            .onSuccess {
                _checklist.postValue(it)
                Log.d("homepillList-server 성공", "${it.data}")
            }
            .onFailure {
                it.printStackTrace()
                Log.d("homepillList-server 실패", "${it}")
            }
    }
}