package team.gdsc.earthgardener.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivityMainBinding
import team.gdsc.earthgardener.presentation.home.HomeFragment
import team.gdsc.earthgardener.presentation.mypage.MyPageFragment
import team.gdsc.earthgardener.presentation.post.PostFormActivity
import team.gdsc.earthgardener.presentation.post.PostFragment

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initBottomNavigationView()
        setBottomNavBarVisibility()
        setFloatingActionButton()
    }

    private fun initBottomNavigationView() {
        binding.bnvMain.setupWithNavController(findNavController())
    }

    private fun findNavController(): NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        return navHostFragment.navController
    }

    private fun setBottomNavBarVisibility() {
        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            binding.bnvMain.visibility = when (destination.id) {
                R.id.homeFragment, R.id.postFragment, R.id.myPageFragment -> View.VISIBLE
                else -> View.GONE
            }
        }
    }

    private fun setFloatingActionButton() {
        binding.fabPost.setOnClickListener {
            startActivity(Intent(this, PostFormActivity::class.java))
        }
    }
}