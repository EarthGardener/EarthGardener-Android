package team.gdsc.earthgardener.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ActivityMainBinding
import team.gdsc.earthgardener.presentation.home.HomeFragment
import team.gdsc.earthgardener.presentation.mypage.MyPageFragment
import team.gdsc.earthgardener.presentation.post.PostFormActivity
import team.gdsc.earthgardener.presentation.post.PostFragment

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val homeFragment: Fragment by lazy { HomeFragment() }
    private val postFragment: Fragment by lazy { PostFragment() }
    private val myPageFragment: Fragment by lazy { MyPageFragment() }

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        initViews()
        setFloatingActionButton()
        initBottomNavigationView()

    }

    private fun setFloatingActionButton() {
        binding.fabPost.setOnClickListener {
            startActivity(Intent(this, PostFormActivity::class.java))
        }
    }

    private fun initViews() {
        bottomNavigationView = binding.bnvMain
    }

    private fun initBottomNavigationView() {
        bottomNavigationView.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.main_menu_home -> setCurrentFragment(homeFragment)
                    R.id.main_menu_post -> setCurrentFragment(postFragment)
                    R.id.main_menu_my_page -> setCurrentFragment(myPageFragment)
                }
                true
            }
            selectedItemId = R.id.main_menu_home
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fcv_main, fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            commit()
        }
    }
}