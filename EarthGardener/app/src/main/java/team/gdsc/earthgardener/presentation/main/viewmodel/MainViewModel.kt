package team.gdsc.earthgardener.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.gdsc.earthgardener.data.model.request.ReqTreeNameSuccessData
import team.gdsc.earthgardener.domain.model.tree.TreeInfoSuccessData
import team.gdsc.earthgardener.domain.repository.tree.TreeInfoRepository
import team.gdsc.earthgardener.domain.repository.tree.TreeNameRepository

class MainViewModel(private val treeInfoRepository: TreeInfoRepository, private val treeNameRepository: TreeNameRepository) : ViewModel() {

    private var _treeInfo = MutableLiveData<TreeInfoSuccessData.TreeInfo>()
    val treeInfo: LiveData<TreeInfoSuccessData.TreeInfo> = _treeInfo

    private var _isLevelUp = MutableLiveData<Boolean>()
    val isLevelUp: LiveData<Boolean> = _isLevelUp

    private var _userNickName = MutableLiveData<String>()
    val userNickName: LiveData<String> = _userNickName

    private var _newTreeName: String = ""
    var newTreeName: String = _newTreeName
        set(value) {
            _newTreeName = value
            field = value
        }

    private var _token: String = ""
    var token: String = _token
        set(value) {
            _token = value
            field = value
        }

    fun getTreeInfo() = viewModelScope.launch {
        runCatching { treeInfoRepository.getTreeInfoResult() }
            .onSuccess { treeInfoSuccessData ->
                // 서버 연동 성공 시
                _treeInfo.postValue(treeInfoSuccessData.data)
                _isLevelUp.postValue(treeInfoSuccessData.levelUp)
                _userNickName.value = treeInfoSuccessData.userNickName

            }
            .onFailure {
                // 서버 연동 실패 시
                it.printStackTrace()
            }
    }

    fun postTreeName() = viewModelScope.launch {
        runCatching { treeNameRepository.postTreeName(ReqTreeNameSuccessData(_newTreeName)) }
            .onSuccess {
                _isLevelUp.postValue(false)
            }
            .onFailure {
                it.printStackTrace()
            }
    }
}