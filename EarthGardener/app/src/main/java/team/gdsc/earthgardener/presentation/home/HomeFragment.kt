package team.gdsc.earthgardener.presentation.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
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
        observeTreeLevelUp()
        observeUserNickName()
        observeTreeInfo()

    }

    private fun observeTreeLevelUp() {
        homeViewModel.isLevelUp.observe(viewLifecycleOwner) { isLevelUp ->
            if (isLevelUp) {
                displayTreeNameDialog()
            }
        }
    }

    private fun initTreeNameDialog(): AlertDialog.Builder {
        val view = View.inflate(context, R.layout.dialog_tree_name, null)
        return AlertDialog.Builder(context)
            .setCancelable(false)
            .setView(view)
    }

    private fun displayTreeNameDialog() {

        val dialogBuilder = initTreeNameDialog()

        val dialog = dialogBuilder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        trackTreeNameTextChanged(dialog)

    }

    private fun trackTreeNameTextChanged(dialog: AlertDialog) {
        val treeNameEditText = dialog.findViewById<EditText>(R.id.et_dialog_tree_name)
        treeNameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                saveNewTreeName(dialog)
            }
        })
    }

    private fun saveNewTreeName(dialog: AlertDialog) {
        val saveButton = dialog.findViewById<AppCompatButton>(R.id.btn_save_name)

        saveButton.isEnabled = true
        saveButton.setBackgroundResource(R.drawable.rectangle_primary_green_radius_12)

        saveButton.setOnClickListener {
            val newTreeName =
                dialog.findViewById<EditText>(R.id.et_dialog_tree_name).text.trim().toString()
            postNewTreeName(newTreeName)
            dialog.dismiss()
        }
    }

    private fun postNewTreeName(newTreeName: String) {
        homeViewModel.newTreeName = newTreeName
        homeViewModel.postTreeName()
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

        setTreeDrawableByTreeLevel(treeLevel)
        setProgressBarByTreeExp(treeExp, treeLevel)
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
            else -> {
                throw Exception()
            }
        }
        binding.tvTreeExp.text = "$treeExp/$maxExp"
    }

    private fun setTreeTotalSum(treeTotalSum: Int) {
        binding.tvTreeTotalCount.text = "$treeTotalSum"
    }

    private fun setTreeMonthlySum(treeMonthlySum: Int) {
        binding.tvTreeMonthlyCount.text = "$treeMonthlySum"
    }

    private fun setTreeDrawableByTreeLevel(treeLevel: Int) {
        when (treeLevel) {
            1 -> binding.ivTree.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.img_tree_level_1
                )
            )
            2 -> binding.ivTree.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.img_tree_level_2
                )
            )
            3 -> binding.ivTree.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.img_tree_level_3
                )
            )
            4 -> binding.ivTree.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.img_tree_level_4
                )
            )
            5 -> binding.ivTree.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.img_tree_level_5
                )
            )
        }
    }

    private fun setProgressBarByTreeExp(treeExp: Int, treeLevel: Int) {
        when (treeLevel) {
            1 -> {
                binding.pbTreeExp.max = 1000
                binding.pbTreeExp.progress = treeExp
            }
            2 -> {
                binding.pbTreeExp.max = 1500
                binding.pbTreeExp.progress = treeExp
            }
            3 -> {
                binding.pbTreeExp.max = 2000
                binding.pbTreeExp.progress = treeExp
            }
            4 -> {
                binding.pbTreeExp.max = 2500
                binding.pbTreeExp.progress = treeExp
            }
            5 -> {
                binding.pbTreeExp.max = 3000
                binding.pbTreeExp.progress = treeExp
            }
        }
    }
}