package team.gdsc.earthgardener.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.FragmentHomeBinding
import team.gdsc.earthgardener.presentation.base.BaseFragment
import team.gdsc.earthgardener.presentation.main.viewmodel.MainViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getTreeInfo()

        observeViewModel()

    }

    private fun observeViewModel() {
        observeUserNickName()
        observeTreeInfo()
    }

    @SuppressLint("SetTextI18n")
    private fun observeUserNickName() {
        homeViewModel.userNickName.observe(viewLifecycleOwner) { nickName ->
            binding.tvGreeting.text = "${nickName}님"
        }
    }

    private fun observeTreeInfo() {
        homeViewModel.treeInfo.observe(viewLifecycleOwner) { treeInfo ->
            val treeName = treeInfo.name
            val treeLevel = treeInfo.level
            val treeExp = treeInfo.exp
            val treeTotalSum = treeInfo.totalSum
            val treeMonthlySum = treeInfo.monthSum

            bindViews(treeName, treeLevel, treeExp, treeTotalSum, treeMonthlySum)

        }
    }

    private fun bindViews(
        treeName: String,
        treeLevel: Int,
        treeExp: Int,
        treeTotalSum: Int,
        treeMonthlySum: Int
    ) {
        setTreeName(treeName)
        setTreeLevel(treeLevel)
        setTreeExp(treeExp, treeLevel)
        setTreeTotalSum(treeTotalSum)
        setTreeMonthlySum(treeMonthlySum)
    }

    private fun setTreeName(treeName: String) {
        binding.tvTreeName.text = treeName
    }

    @SuppressLint("SetTextI18n")
    private fun setTreeLevel(treeLevel: Int) {
        binding.tvTreeLevel.text = "Lv.$treeLevel"
    }

    @SuppressLint("SetTextI18n")
    private fun setTreeExp(treeExp: Int, treeLevel: Int) {
        val maxExp: Int = when (treeLevel) {
            1 -> 1000
            2 -> 1500
            3 -> 2000
            4 -> 2500
            5 -> 3000
            else -> { throw Exception() }
        }
        binding.tvTreeExp.text = "$treeExp/$maxExp"
    }

    private fun setTreeTotalSum(treeTotalSum: Int) {
        binding.tvTreeTotalCount.text = "$treeTotalSum"
    }

    private fun setTreeMonthlySum(treeMonthlySum: Int) {
        binding.tvTreeMonthlyCount.text = "$treeMonthlySum"
    }
}